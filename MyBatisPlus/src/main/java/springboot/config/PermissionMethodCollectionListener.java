package springboot.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.annotations.RequiresPermission;
import springboot.entity.Permission;
import springboot.mapper.PermissionMapper;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 系统启动时收集全部的权限方法，同步到t_permission
 */
@Component
public class PermissionMethodCollectionListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    /**
     * 这里演示通过ApplicationContextAware注入，你也可以直接使用@AutoWired
     */
    private ApplicationContext applicationContext;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 得到t_permission已有的所有权限方法
        Set<String> permissionsFromDB = new HashSet<>();
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<>());
        if (CollectionUtils.isNotEmpty(permissions)) {
            permissions.forEach(permission -> permissionsFromDB.add(permission.getMethod()));
        }

        // 遍历所有Controller
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Controller.class);
        Collection<Object> beans = beanMap.values();
        for (Object bean : beans) {
            Class<?> controllerClazz = bean.getClass();

            // 如果Controller上有@RequiresPermission，那么所有接口都要收集(isApiMethod)，否则只收集打了@Permission的接口(hasPermissionAnnotation)
            Predicate<Method> filter = AnnotationUtils.findAnnotation(controllerClazz, RequiresPermission.class) != null
                    ? this::isApiMethod
                    : this::hasPermissionAnnotation;

            // 过滤出Controller中需要权限验证的method
            Set<String> permissionMethodsWithinController = getPermissionMethodsWithinController(
                    controllerClazz.getName(),
                    controllerClazz.getMethods(),
                    filter
            );

            for (String permissionMethodInMemory : permissionMethodsWithinController) {
                // 如果是新增的权限方法
                if (!permissionsFromDB.contains(permissionMethodInMemory)) {
                    Permission permission = new Permission();
                    permission.setModule("");
                    permission.setName("");
                    permission.setMethod(permissionMethodInMemory);
                    permissionMapper.insert(permission);
                }
            }
        }

    }

    private Set<String> getPermissionMethodsWithinController(String controllerName, Method[] methods, Predicate<Method> filter) {

        return Arrays.stream(methods)
                .filter(filter)
                .map(method -> {
                    StringBuilder sb = new StringBuilder();
                    String methodName = method.getName();
                    return sb.append(controllerName).append("#").append(methodName).toString();
                })
                .collect(Collectors.toSet());
    }

    private boolean hasPermissionAnnotation(Method method) {
        return AnnotationUtils.findAnnotation(method, RequestMapping.class) != null
                && AnnotationUtils.findAnnotation(method, RequiresPermission.class) != null;
    }

    private boolean isApiMethod(Method method) {
        return AnnotationUtils.findAnnotation(method, RequestMapping.class) != null;
    }

}
