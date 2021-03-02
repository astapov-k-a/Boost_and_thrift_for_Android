cmake_minimum_required(VERSION 3.1)
project(bridge C CXX)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)
set(CMAKE_CXX_FLAGS_RELEASE "-O3")

add_definitions(-DDEFAULT_CHANNEL=1)

set(BUILD_SHARED_LIBS OFF)

set(WITH_ZLIB ON)
set(WITH_STATIC_LIB ON)
set(HAVE_CONFIG_H ON)
set(LIB_INSTALL_DIR "${CMAKE_CURRENT_SOURCE_DIR}/prebuilt/${ANDROID_ABI}")
add_subdirectory(thrift)
include_directories(thrift/src)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
set(EXECUTABLE_OUTPUT_PATH      "${CMAKE_CURRENT_SOURCE_DIR}/../../../output/${ANDROID_ABI}")

#for debug stack trace
#set(ASAN_OPTIONS "symbolize=1")
#set(ASAN_SYMBOLIZER_PATH "$(shell which llvm-symbolizer")
#set(CMAKE_EXE_LINKER_FLAGS_DEBUG "${CMAKE_EXE_LINKER_FLAGS_DEBUG} -fsanitize=address")
#set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -fsanitize=address -fno-omit-frame-pointer")

set(ELF_SOURCE_LIST_CPP        
    main.cpp
        )

add_executable( # Sets the name of executable
        sample_exec

        # Provides a relative path to your source file(s).
        ${ELF_SOURCE_LIST_CPP}
)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
                      

add_library( lib_thrift STATIC IMPORTED )
set_target_properties( lib_thrift
        PROPERTIES
        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/prebuilt/${ANDROID_ABI}/libthriftz.a )

target_link_libraries( # Specifies target executable.
        sample_exec

        # Links the target library to the log library
        # included in the NDK.
        #${log-lib}

        #make 'em or use precompiled
                   
        thriftz_static
        #lib_thrift
        )
