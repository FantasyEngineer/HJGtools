#设置cmake最小的版本
cmake_minimum_required(VERSION 3.10)

add_library( # 设置一个库的名字
        Native-lib

        # 设置是否作为一个共享库
        SHARED

        # 提供到源文件的相对路径。现在是同一目录
        Native-lib.cpp)


find_library( # 设置path变量的名称。系统的log库
        log-lib

        # 指定您希望CMake定位的NDK库的名称。
        log)


target_link_libraries( #指定目标库。
        Native-lib
        # 将目标库链接到日志库
        #包含在NDK中。
        #${log-lib}#
        )