package main;



import java.io.BufferedReader;
import java.io.FileReader;

import data_structures.ArrayList;
import interfaces.List;

public class Ballot {
	
	private int ballotNumber;
	private List<Integer> votes;
	private List<Integer> ranks;
	
	/* Creates a ballot based on the line it receives. The format for line is
	id#,candidate_name . It also receives a List of all the candidates in the
	elections.*/
	
	/*
	 * Split the line in order to get the number of the ballot and then split them again, 
	 * this time with the colon, in order to get the idNumber of the candidate and its 
	 * respective rank given by the voters.
	 */
	public Ballot(String line, List<Candidate> candidates) {
		
		String[] parts = line.split(",");
		
		this.ballotNumber = Integer.parseInt(parts[0]);
		
		this.votes = new ArrayList<Integer>();
		
		this.ranks = new ArrayList<Integer>();
			
		for(int i = 1; i < parts.length; i++) {
			
			String[] idNumberAndRank = parts[i].split(":");
			
			int idNumber = Integer.parseInt(idNumberAndRank[0]);
			
			int rank = Integer.parseInt(idNumberAndRank[1]);
			
			this.votes.add(idNumber);
			
			this.ranks.add(rank);
			
		}
		
		
	}
	
	
	
	// Returns the ballot number
	public int getBallotNum() {
		
		return this.ballotNumber;
		
	}
	
	
	
	//Returns the rank for that candidate, if no rank is available return -1
	
	/*
	 * By getting the index of the candidateID from the arrayList that I created earlier and
	 * then summing it by 1, I get the number equivalent to the rank. This is because the
	 * ranking are in ascending order i.e. 1,2,3,4,5...
	 */
	public int getRankByCandidate(int candidateID) {
		
		int index = this.votes.firstIndex(candidateID);
		
		if(index == -1) {
			
			return -1;
			
		}
		
		else {
			
			return index + 1;
			
		}
		
	}
	
	
	/*
	 * Since the ranks are in ascending order, all I had to do was get the index by getting
	 * the number of the rank and subtracting it by one and with that index, search its 
	 * respective  id number in the id arrayList.
	 */
	//Returns the candidate with that rank, if no candidate is available return -1.
	public int getCandidateByRank(int rank) {
		
	    int index = rank - 1; // Adjusting rank to array index
	    
	    if (index < 0 || index >= votes.size()) {
	    	
	        return -1; // Rank out of bounds
	        
	    }
	    
	    return votes.get(index);
		
	}
	
	
	
	// Eliminates the candidate with the given id
	public boolean eliminate(int candidateId) {
		
		return this.votes.remove((Integer) candidateId);
		
	}
	
	
	/*
	 * 
	 */
	/* Returns an integer that indicates if the ballot is: 0 – valid, 1 – blank or 2 -
	invalid */
	public int getBallotType() {
		

	    if (this.votes.isEmpty()) {
	        return 1; 
	    }

	    int numCandidates = votes.size();
	    int maxRank = 0;

	    ArrayList<Integer> differentCandidates = new ArrayList<>();
	    ArrayList<Integer> differentRanks = new ArrayList<>();

	    int prevRank = -1; 

	    for (int i = 0; i < numCandidates; i++) {
	        int candidateID = votes.get(i);
	        


	        if (differentCandidates.contains(candidateID)) {
	            return 2; 
	        }

	        
	        differentCandidates.add(candidateID);


	        int rank = ranks.get(i);

	        if (rank > numCandidates || differentRanks.contains(rank)) {
	            return 2; 
	        }

	        if (rank > maxRank) {
	            maxRank = rank;
	        }

	        differentRanks.add(rank);


	        if (prevRank != -1 && rank - prevRank > 1) {
	            return 2; 
	        }

	        prevRank = rank;
	    }


	    if (maxRank > numCandidates) {
	        return 2; 
	    }

	    return 0; 
		
	}
	
	

    // Method to test creating ballots
	public static void main(String[] args) {
		
		List<Candidate> candidates2 = new ArrayList<Candidate>(10);
		List<Candidate> candidates3 = new ArrayList<Candidate>(10);
		List<Candidate> candidates4 = new ArrayList<Candidate>(10);
		
		
	
		
		System.out.println("Test 2: \n");
		candidates2.add(new Candidate("1,Ron Stoppable"));
		candidates2.add(new Candidate("2,Kim Possible"));
		candidates2.add(new Candidate("3,Rufus Stoppable"));
		candidates2.add(new Candidate("4,Ben Tenison"));
		candidates2.add(new Candidate("5,Timmy Turner"));
		candidates2.add(new Candidate("6,Danny Fenton"));
		candidates2.add(new Candidate("7,Tommy Pickles"));
		candidates2.add(new Candidate("8,P Lankton"));
		candidates2.add(new Candidate("9,Jimmy Neutron"));
		candidates2.add(new Candidate("10,Luz Noceda"));
		Ballot ballot2 = new Ballot("2158,7:1,5:2,1:3,9:4,4:5,2:6,8:7,6:8,10:9,3:10", candidates2);

//		Should print 2158.
		System.out.println(ballot2.getBallotNum());
		
//		Should print 0.
		System.out.println(ballot2.getBallotType());
		
//		Should print 3.
		System.out.println(ballot2.getCandidateByRank(10));
		
//		Should print 2.
		System.out.println(ballot2.getCandidateByRank(6));
		
//		Should print 9.
		System.out.println(ballot2.getRankByCandidate(10));
		
//		Should print 3.
		System.out.println(ballot2.getRankByCandidate(1));
		
		
		


		System.out.println("\nTest 3: \n");
		candidates3.add(new Candidate("1,Ron Stoppable"));
		candidates3.add(new Candidate("2,Kim Possible"));
		candidates3.add(new Candidate("3,Rufus Stoppable"));
		candidates3.add(new Candidate("4,Ben Tenison"));
		candidates3.add(new Candidate("5,Timmy Turner"));
		candidates3.add(new Candidate("6,Danny Fenton"));
		candidates3.add(new Candidate("7,Tommy Pickles"));
		candidates3.add(new Candidate("8,P Lankton"));
		candidates3.add(new Candidate("9,Jimmy Neutron"));
		candidates3.add(new Candidate("10,Luz Noceda"));
		Ballot ballot3 = new Ballot("489,9:1,7:2,10:3,5:4", candidates3);

//		Should print 489.
		System.out.println(ballot3.getBallotNum());
		
//		Should print 0.
		System.out.println(ballot3.getBallotType());
		
//		Should print 10.
		System.out.println(ballot3.getCandidateByRank(3));
		
//		Should print -1.
		System.out.println(ballot3.getCandidateByRank(6));
		
//		Should print 2.
		System.out.println(ballot3.getRankByCandidate(7));
		
//		Should print -1.
		System.out.println(ballot3.getRankByCandidate(3));


		System.out.println("\nTest 4: \n");
		candidates4.add(new Candidate("1,Ron Stoppable"));
		candidates4.add(new Candidate("2,Kim Possible"));
		candidates4.add(new Candidate("3,Rufus Stoppable"));
		candidates4.add(new Candidate("4,Ben Tenison"));
		candidates4.add(new Candidate("5,Timmy Turner"));
		candidates4.add(new Candidate("6,Danny Fenton"));
		candidates4.add(new Candidate("7,Tommy Pickles"));
		candidates4.add(new Candidate("8,P Lankton"));
		candidates4.add(new Candidate("9,Jimmy Neutron"));
		candidates4.add(new Candidate("10,Luz Noceda"));
		Ballot ballot4 = new Ballot("582,7:1,5:2,1:3,9:4,4:5,2:9", candidates4);

//		Should print 582.
		System.out.println(ballot4.getBallotNum());
		
//		Should print 2.
		System.out.println(ballot4.getBallotType());
		

	}
	

	

}



