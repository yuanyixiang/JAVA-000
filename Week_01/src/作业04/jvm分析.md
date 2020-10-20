###### 分析jvm

```
Attaching to process ID 17565, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.131-b11

using thread-local object allocation.
Parallel GC with 8 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 2147483648 (2048.0MB)
   NewSize                  = 44564480 (42.5MB)
   MaxNewSize               = 715653120 (682.5MB)
   OldSize                  = 89653248 (85.5MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 252706816 (241.0MB)
   used     = 86079680 (82.09197998046875MB)
   free     = 166627136 (158.90802001953125MB)
   34.063062232559645% used
From Space:
   capacity = 17825792 (17.0MB)
   used     = 0 (0.0MB)
   free     = 17825792 (17.0MB)
   0.0% used
To Space:
   capacity = 19398656 (18.5MB)
   used     = 0 (0.0MB)
   free     = 19398656 (18.5MB)
   0.0% used
PS Old Generation
   capacity = 132644864 (126.5MB)
   used     = 28101816 (26.79998016357422MB)
   free     = 104543048 (99.70001983642578MB)
   21.185755070019145% used

25191 interned Strings occupying 2337616 bytes.

```

jmap -heap pid 

![截屏2020-10-20 下午2.51.35](/Users/rd-yyx/Desktop/java/JAVA-000/JAVA-000/Week_01/src/作业01/截屏2020-10-20 下午2.51.35.png)

###### 分析：

自写的后端，没调过jvm

jvm使用的jdk8的默认gc，Parallel GC，根据visualGC上有写到策略ParScav:MSC，年轻代使用的是复制算法，老年代使用的是标记清楚整理算法。

并行gc使用的是线程数是默认的线程数，根据本机8核给的8个线程

堆内存在物理内存超过1g时为物理内存的4分之一，本机是8g内存，分配了2g的内存

老年代和年轻代比例为2比1，所以年轻代占堆内存的3分之一，所以为682.5mb

eden区和from，to区的比例为8:1:1

元空间的默认最大为无限大，所以调优中应该将元空间的最大大小调为一个值以免将堆内存的空间全部用掉

其他的调优没有太多的理解

