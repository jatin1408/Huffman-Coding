import java.util.*;

public class HuffmanCoding {
    static class HuffmanNode{
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode  right;
        public HuffmanNode(int data, char c, HuffmanNode left, HuffmanNode right) {
            this.data = data;
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }
    static class MyComparator implements Comparator<HuffmanNode>{
        @Override
        public int compare(HuffmanNode o1, HuffmanNode o2) {
            return o1.data-o2.data;
        }
    }
    public static Map<Character,Integer> freq(String str){
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<str.length();i++){
            char temp=str.charAt(i);
            if(map.containsKey(temp)){
                int t=map.get(temp)+1;
               map.replace(temp,t);
            }
            else {
                map.put(temp,1);
            }
        }

        return map;
    }
    public  void print(HuffmanNode root,String str){
        if(root.left==null && root.right==null && Character.isLetter(root.c)){
            System.out.println(root.c+" : "+str);
            return;
        }
        print(root.left,str+"0");
        print(root.right,str+"1");
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Map<Character,Integer> map=freq(sc.nextLine());
        Set< Map.Entry< Character,Integer> > st = map.entrySet();
        int temp=map.size();
        int[] n=new int[temp];
        char[] c=new char[temp];
        int count=0;
        for(Map.Entry<Character,Integer> m:st){

            n[count]=m.getValue();
            c[count]=m.getKey();
            count++;
        }



        PriorityQueue<HuffmanNode> priorityQueue=new PriorityQueue<>(temp,new MyComparator());
        for(int i=0;i<temp;i++){
            HuffmanNode node=new HuffmanNode(n[i],c[i],null,null);
            priorityQueue.add(node);
        }
        HuffmanNode root=null;
        while (priorityQueue.size()!=1){
            HuffmanNode x= priorityQueue.poll();
            HuffmanNode y=priorityQueue.poll();
            int data=x.data+y.data;
            HuffmanNode f=new HuffmanNode(data,'-',x,y);
            root=f;
            priorityQueue.add(f);
        }
        HuffmanCoding h=new HuffmanCoding();
        h.print(root,"");
    }
}