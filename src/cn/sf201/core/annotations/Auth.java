package cn.sf201.core.annotations;

import java.lang.annotation.*;

@Documented
@Target(
        { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth
{
    String name();
}