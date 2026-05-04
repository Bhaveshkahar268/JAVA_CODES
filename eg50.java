import java.util.*;

class eg50psp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size
        int n = sc.nextInt();

        // Input array
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Frequency map
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Convert array to Integer list for sorting
        Integer[] temp = new Integer[n];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }
for(int i:arr) System.out.print(i + " ");
System.out.println();
for(int i: freqMap.keySet()) System.out.println(i + " : "+freqMap.get(i));

        // Sort based on frequency, then value
        Arrays.sort(temp, (a, b) -> {
            int freqA = freqMap.get(a);
            int freqB = freqMap.get(b);

            if (freqA != freqB) {
                return freqA - freqB; // ascending frequency
            } else {
                return a - b; // ascending value
            }
        });

        // Output result
        for (int num : temp) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}

