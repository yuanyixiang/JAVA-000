##### 不同gc的总结

java -jar  -Xmx512m  -Xms512m gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  46689.62

java -jar -XX:+UseConcMarkSweepGC -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  45331.12

java -jar -XX:+UseG1GC -Xmx512m -Xms512m gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  41777.26

java -jar -Xmx3g -Xms3g gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  45263.62

java -jar -XX:+UseConcMarkSweepGC -Xmx3g -Xms3g gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  47420.08

java -jar -XX:+UseG1GC -Xmx3g -Xms3g  gateway-server-0.0.1-SNAPSHOT.jar

Requests/sec:  44485.93



<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gk30nezo51j31xs0kq4qp.jpg" alt="image-20201026203819794" style="zoom:33%;" />![image-20201026203907023](https://tva1.sinaimg.cn/large/0081Kckwgy1gk30o6btykj31yg0i84h6.jpg)

<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gk30nezo51j31xs0kq4qp.jpg" alt="image-20201026203819794" style="zoom:33%;" />![image-20201026203907023](https://tva1.sinaimg.cn/large/0081Kckwgy1gk30o6btykj31yg0i84h6.jpg)

<img src="https://tva1.sinaimg.cn/large/0081Kckwgy1gk30pgoh4wj31ss074n0j.jpg" alt="image-20201026204017476" style="zoom:33%;" />

![image-20201026205007733](https://tva1.sinaimg.cn/large/0081Kckwgy1gk30zve5jpj31dw0dmh39.jpg)

首先我对不同gc的使用wrk压测工具进行了测试，与老师讲的情况稍有不同，反而在低内存分配的情况默认的并行gc的吞吐量更高，也验证了一个道理虽然不知道是不是对的因为这个程序没有太多对象的产生，只是在进行访问，当访问量没达到一定的数量的时候cms其实不会发生full gc，也就不会触发cms的垃圾回收器而是young gc使用的parnew gc，但是因为parnew是以响应速度优先所以一般情况下并行的吞吐量要高一些，通过上面图片对随机对象产生垃圾回收也能看出，同样在2g的内存下并行和cms的回收次数差不多，但是g1的明显要多一些，因为g1gc更在乎响应速度低延迟而且在默认情况下，新生代老年代默认达到一定百分比就会产生gc，所以g1gc的吞吐量会稍低一些，但每次垃圾回收的毫秒也能从图中看出其实是很低的。

##### 根据上课，同学交流，以及官方文档的一些小总结

###### parallel

java8默认使用的并行gc更在乎高吞吐量，是最大暂停时间目标、吞吐量目标和最小占用空间目标，默认分配堆内存4分之一的物理内存，young区默认64分之一的物理内存，一般用于多核cpu，并行垃圾在内存不是特别大的情况较为优。

###### cms

cms的老年代使用的cms，新生代使用的parnew，更在乎低延迟，通过阅读官方文档会出现并发模式失败，并发模式失败只是说当无法在旧代充满之前完成回收之前就会暂停应用程序完成收集，然后后面有在旧代充满时应用程序停止时会使用serial垃圾回收机制，和g1垃圾回收类似的退化现象，所以要注意老年代的大小。

###### g1

g1垃圾回收可以设置垃圾回收的最大时间，并且也是低延迟为主线，从压测和垃圾回收日志都能看出g1垃圾回收次数会变多，但是每次垃圾回收的时间都很小，我觉得之所以大内存的情况下推荐使用g1是因为大内存出现垃圾回收的次数相较在小内存时会更少，所以g1有每次回收时低延迟的优势，而低内存的时候总是会发生垃圾回收那么产生了很多次垃圾回收可能依然还是会因为多次垃圾回收的缘故导致吞吐量的下降，并且因为g1垃圾回收默认老年代大于%45的时候就会触发垃圾回收。