public class Hello {

  public void hello(){
      int a,b,c,d;
      for(int i = 2;i < 10; i++){
          if(i % 2 == 0){
              a = i+2;
              b = i-2;
              c = i*2;
              d = i/2;
              System.out.println(a+b+c+d);
          }
      }
  }

}
