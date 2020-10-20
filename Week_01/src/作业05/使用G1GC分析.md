```
Attaching to process ID 18520, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.131-b11

using thread-local object allocation.
Garbage-First (G1) GC with 8 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 2147483648 (2048.0MB)
   NewSize                  = 1363144 (1.2999954223632812MB)
   MaxNewSize               = 1287651328 (1228.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 1048576 (1.0MB)

Heap Usage:
G1 Heap:
   regions  = 2048
   capacity = 2147483648 (2048.0MB)
   used     = 44036088 (41.99608612060547MB)
   free     = 2103447560 (2006.0039138793945MB)
   2.050590142607689% used
G1 Young Generation:
Eden Space:
   regions  = 14
   capacity = 76546048 (73.0MB)
   used     = 14680064 (14.0MB)
   free     = 61865984 (59.0MB)
   19.17808219178082% used
Survivor Space:
   regions  = 7
   capacity = 7340032 (7.0MB)
   used     = 7340032 (7.0MB)
   free     = 0 (0.0MB)
   100.0% used
G1 Old Generation:
   regions  = 22
   capacity = 50331648 (48.0MB)
   used     = 22015992 (20.99608612060547MB)
   free     = 28315656 (27.00391387939453MB)
   43.74184608459473% used

16108 interned Strings occupying 2166072 bytes.
```

###### 分析：

G1HeapRegionSize         = 1048576 (1.0MB)

堆内存的每个区域默认为1mb

将2个g的内存，2048mb分成了2048个regions