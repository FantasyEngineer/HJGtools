
//
// Created by Administrator on 2021/3/26.
//
#include <jni.h>
#include <string>


typedef jobject pJobject;
extern "C" JNIEXPORT jstring

JNICALL
Java_com_hjg_hjgtools_activity_jni_HelloJni_helloC(
        JNIEnv *env,
        jobject jobj) {
    std::string hello = "Hello from C++ : ";
    return env->NewStringUTF(hello.c_str());
}
