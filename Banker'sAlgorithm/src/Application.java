import java.util.Scanner;

public class Application {
	static int[] S_Sequence = new int[100];
	static int[] Un_Sequence= new int[100];

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Resource Request For Process i - Process Number .
	// Requesti = request Array for process Pi.
	static boolean RequestResourse(int Available[], int Allocation[][], int Need[][], int Request[], int ProcessNumber,
			int Processes, int Resourses) {
		int n = Processes;
		int m = Resourses;
		int[] AvailableTemp = new int[m];
		int[][] AllocationTemp = new int[n][m];
		int[][] NeedTemp = new int[n][m];
		int[] Req = new int[m];
		int Pro_Number = ProcessNumber;
///////////////////////////////////////////////////////////////////		
		for (int i = 0; i < m; i++) {
			AvailableTemp[i] = Available[i];
		}
//////////////////////////////////////////////////////////////////		
		for (int i = 0; i < m; i++) {
			Req[i] = Request[i];
		}
//////////////////////////////////////////////////////////////////		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				AllocationTemp[i][j] = Allocation[i][j];
			}
		}
/////////////////////////////////////////////////////////////////		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				NeedTemp[i][j] = Need[i][j];
			}
		}
////////////////////////////////////////////////////////////////		
		boolean Check = true;
		// For first Check
		for (int i = 0; i < m; i++) {
			if (NeedTemp[Pro_Number][i] < Req[i]) {
				Check = false; // error condition
			}

		}
		if (Check) {
			for (int i = 0; i < m; i++) 
				if (AvailableTemp[i] < Req[i]) 
					Check = false; // must be wait .
				
			
			// if en all check is true .
			// Step Three .
			if (Check) {
				for (int i = 0; i < m; i++) {
					AvailableTemp[i] = AvailableTemp[i] - Req[i];
					AllocationTemp[Pro_Number][i] = AllocationTemp[Pro_Number][i] + Req[i];
					NeedTemp[Pro_Number][i] = NeedTemp[Pro_Number][i] - Req[i];

				}
				System.out.println("No Of processes : " + n + "No Of Resources : " + m +"\n");
				System.out.println("Allocation Before Safe Algorthim : " + "\n") ;
				for (int i = 0 ; i < n ; i++ )
				{
					for (int j = 0 ; j < m ; j++)
					{
						System.out.print(AllocationTemp[i][j] + " ") ; 
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				System.out.println("Available Before Safe Algorithm : ") ;
				for (int i =0 ; i < m; i++ )
				{
					System.out.print(AvailableTemp[i]+" ");
				}
				System.out.print("\n");
				System.out.println("Need Before Safe Algorithm  :");
				for (int i = 0 ; i < n ; i++ )
				{
					for (int j = 0 ; j < m ; j++)
					{
						System.out.print(NeedTemp[i][j] + " ") ; 
					}
					System.out.print("\n");
				}
				System.out.print("\n");
				if (Safe(n, m, AllocationTemp, NeedTemp, AvailableTemp)) 
				{	System.out.println("testing.") ; 
					return true;
				}
				else { 
				
					System.out.println("this request cant be granted . ") ;
				}
			}
			    else 
			    {
				System.out.println("P" + Pro_Number + "this process must be wait in request algorthim . ");
			    }
		}
		else
			System.out.println("Raise error condition , since process has exceeded its maximum claim. ");
		return false;

	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Safety Algorithm . 
// Work ->>> be length of M - Number of Resources . 
// Finish ->>> be length of N - Number of Processes .
	static boolean Safe(int Processes, int Resourses, int Allocation[][], int Need[][], int Available[]) {
		int n = Processes;
		int m = Resourses;
		int cnt = 0 ; 
		int cnt2 = 0 ; 
		int[][] AllocationTemp = new int[n][m];
		int[][] NeedTemp = new int[n][m];
		boolean[] Finish ; 
		int[] Work  = new int [m]; 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				AllocationTemp[i][j] = Allocation[i][j];
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				NeedTemp[i][j] = Need[i][j];
			}
		}
		// Initialize Work .
		for (int i = 0; i < m; i++) {
			Work[i] = Available[i];
		}
		System.out.println("Available: ") ;
		for (int i =0 ; i < m; i++ )
		{
			System.out.print(Work[i]+" ");
		}
		System.out.print("\n");
		System.out.println("Allocation:");
		for (int i = 0 ; i < n ; i++ )
		{
			for (int j = 0 ; j < m ; j++)
			{
				System.out.print(Allocation[i][j] + " ") ; 
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.println("Need :");
		for (int i = 0 ; i < n ; i++ )
		{
			for (int j = 0 ; j < m ; j++)
			{
				System.out.print(Need[i][j] + " ") ; 
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		// Initialize Finish For the first Time == False
		Finish = new boolean[n];
		for (int i = 0; i < n; i++) {
			Finish[i] = false;
		}
		while(cnt<n&&cnt2<n) {
			for (int i = 0; i < n; i++) {
				boolean Check = true; 
				// Step Two .
				if (Finish[i] == false) // not allocated
				{
					for (int j = 0; j < m; j++) {
						if (NeedTemp[i][j] > Work[j]) {
							Check = false; // must be wait .

						}
						
						else if (NeedTemp[i][j] == Work[j])
						{
							Check = true ; 
						}
						
					 
					}
					if (Check) // if available resources are greater than el needed .
					{
						for (int j = 0; j < m; j++) {
							// Step 3
							Work[j] = Work[j]+ AllocationTemp[i][j];
						}
						System.out.println("P" + i + " this process must be in safe sequence in safe algorithm . ");
						S_Sequence[cnt++] = i;
						Finish[i] = true;

					} 
					else  {
					    System.out.println("P" + i + " this process must be wait in safe algorithm . ");
						Un_Sequence[cnt] = i;
					
					}

				}

			}
			cnt2++;
			}

		if (cnt > n) {
			return false;
		} else {
			return true;
		}

	}
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		int n; // number of processes .
		int m; // number of resources .
		int[] Available;
		int[] RequestResources;
		int processnumber;
		Scanner Scan = new Scanner(System.in);
		System.out.println("***************************Welcome To Banker's Algorithm.**************************");
		System.out.println("**************you have to initialize your data so***********************");
		System.out.println("Enter The Number Of Processe : ");
		n = Scan.nextInt();
		System.out.println("Enter The Number Of Resources : ");
		m = Scan.nextInt();

		Available = new int[m];
		RequestResources = new int[m];
		System.out.println("Enter Available Resources : ");
		for (int i = 0; i < m; i++) {
			Available[i] = Scan.nextInt();
		}
		int[][] Max;
		Max = new int[n][m];
		System.out.println("Enter Maxmimum : ");
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				Max[x][y] = Scan.nextInt();
			}
		}
		int[][] Allocation;
		Allocation = new int[n][m];
		System.out.println("Enter Allocation :  ");
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				Allocation[x][y] = Scan.nextInt();
			}
		}
		int[][] Need;
		Need = new int[n][m];
		System.out.println("Need : ");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				Need[i][j] = Max[i][j] - Allocation[i][j];
			}

		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(Need[i][j] + " ");
			}
			System.out.println();
		}
		int choice;

		System.out.println(
				"Choose Your Number \n-if you wanna check if your system is in a safe state or not enter 1 .\n-if you wanna to request a resource and check if your system is in a safe state or not, after requesting enter 2. \n-If you wanna exit the program enter 3 \n>> ");
		choice = Scan.nextInt();
		if (choice == 1) {
			System.out.println("Safety Algorithm. ");
			if (Safe(n, m, Allocation, Need, Available)) {
				System.out.print("Safe sequence:");
				for (int i = 0; i < n; i++) {
					System.out.print("P" + S_Sequence[i]);
					if (i != n - 1) {
						System.out.print("->>");

					}
				}
			} else {
				System.out.print("this system is in unsafe state. ");
			}
		} else if (choice == 2) {
			System.out.println("Request Resource for process Algorithm. ");
			System.out.println("so, you have to enter request resource ");
			for (int i = 0; i < m; i++) {
				RequestResources[i] = Scan.nextInt();
			}
			System.out.println("then,enter process number : ");
			processnumber = Scan.nextInt();
			if (RequestResourse(Available, Allocation, Need, RequestResources, processnumber, n, m)) {
                System.out.println("This request can be granted. ");
				System.out.println("Loading.");
				System.out.println("Safe Sequence After Requesting.");
				for (int i = 0; i < n; i++) {
					System.out.print("P" + S_Sequence[i]);
					if (i != n - 1) {
						System.out.print("->>");

					}
				}

			} else {
				System.out.println("this system in unsafe state .");
			}

		} else if (choice == 3) {
			System.out.println("Thank You.");
			System.exit(0);

		}
	}
}
/* Maximum 
7
5
3
3
2
2
9
0
2
2
2
2
4
3
3 
*/
/*Allocation 
0
1
0
2
0
0
3
0
2
2
1
1
0
0
2 
*/
// testing for request - 1 0 2 and Process 1  ( P1->>P3->>P4->>P0->>P2 )
// testing for request - 0 2 0 and Process 0  ( P1->>P3->>P0->>P2->>P4 )
// testing for request - 3 3 0 and process 4  ( P1->>P2->>P4->>P3->>P0 )