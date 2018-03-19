package cn.sf201.core.utils;
import  java.lang.annotation.Documented;   
import  java.lang.annotation.ElementType;   
import  java.lang.annotation.Retention;   
import  java.lang.annotation.RetentionPolicy;   
import  java.lang.annotation.Target;

@Target(ElementType.TYPE)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented 
public @interface InitNameAnnotation {
	String value();
}
