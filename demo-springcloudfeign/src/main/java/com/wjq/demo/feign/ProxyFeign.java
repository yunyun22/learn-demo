//package com.wjq.demo.feign;
//
//import feign.Feign;
//import feign.ReflectiveFeign;
//import feign.Target;
//
//public class ProxyClientFeign extends Feign {
//
//
//    private ReflectiveFeign reflectiveFeign;
//
//    @Override
//    public <T> T newInstance(Target<T> target) {
//
//        Class<T> type = target.type();
//
//        A annotation = type.getAnnotation(ProxyClient.class);
//
//        if (annotation!=null){
//
//            new ProxyTarget();
//        }
//
//
//        return reflectiveFeign.newInstance(target);
//    }
//}
