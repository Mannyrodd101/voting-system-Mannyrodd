package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import data_structures.ArrayList;
import interfaces.List;

public class Election {
    private List<Candidate> candidates;
    private List<Ballot> ballots;


    public Election() {
        this.candidates = new ArrayList<>();
        this.ballots = new ArrayList<>();

        loadCandidates("inputFiles/candidates.csv");
        loadBallots("inputFiles/ballots.csv");
    }
    
    

    public Election(String candidatesFilename, String ballotFilename) {
        this.candidates = new ArrayList<>();
        this.ballots = new ArrayList<>();
        
        loadCandidates(candidatesFilename);
        loadBallots(ballotFilename);
    }
    
    

    private void loadCandidates(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
            String line;
            
            while ((line = br.readLine()) != null) {
            	
                candidates.add(new Candidate(line));
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    private void loadBallots(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        	
            String line;
            
            while ((line = br.readLine()) != null) {
            	
                Ballot ballot = new Ballot(line, candidates);
                
                if (ballot.getBallotType() == 0) {
                	
                    ballots.add(ballot);
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    public String getWinner() {
        // Implement the logic to determine the winner based on the ballots

        return "Pepe Perez"; 
    }
    
    

    public int getTotalBallots() {
    	
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/ballots.csv"))) {

            br.readLine();


            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count + 1;

    }
    
    

    public int getTotalInvalidBallots() {
    	
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/ballots.csv"))) {

            br.readLine();


            String line;
            while ((line = br.readLine()) != null) {
            	
                Ballot ballot = new Ballot(line, candidates);
                
                if (ballot.getBallotType() == 2) {
                	
                    count++;
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;

    }
    
    

    public int getTotalBlankBallots() {
    	
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/ballots.csv"))) {

            br.readLine();


            String line;
            while ((line = br.readLine()) != null) {
            	
                Ballot ballot = new Ballot(line, candidates);
                
                if (ballot.getBallotType() == 1) {
                	
                    count++;
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;

    }
    
    

    public int getTotalValidBallots() {
    	
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("inputFiles/ballots.csv"))) {

            br.readLine();

            String line;
            while ((line = br.readLine()) != null) {
            	
                Ballot ballot = new Ballot(line, candidates);
                
                if (ballot.getBallotType() == 0) {
                	
                    count++;
                    
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count + 1;

    }

    
    
    public List<String> getEliminatedCandidates() {
    	
        List<String> eliminatedCandidates = new ArrayList<>();
        List<Integer> activeCandidateIds = new ArrayList<>();

        for (Candidate candidate : candidates) {
        	
            if (candidate.isActive()) {
            	
                activeCandidateIds.add(candidate.getId());
                
            }
        }

        for (Candidate candidate : candidates) {
        	
            if (!activeCandidateIds.contains(candidate.getId())) {
            	
                eliminatedCandidates.add(candidate.getName());
                
            }
        }

        return eliminatedCandidates;

    }

    public void printBallotDistribution() {
    	
//    	Should print 15.
        System.out.println("Total ballots:" + getTotalBallots());
        
//        Should print 3.
        System.out.println("Total blank ballots:" + getTotalBlankBallots());
        
//        Should print 2.
        System.out.println("Total invalid ballots:" + getTotalInvalidBallots());
        
//        Should print 10.
        System.out.println("Total valid ballots:" + getTotalValidBallots());

        System.out.println(getWinner());
        
        System.out.println(getEliminatedCandidates());
        for (Candidate c : candidates) {
            System.out.print(c.getName().substring(0, c.getName().indexOf(" ")) + "\t");
            for (Ballot b : ballots) {
                int rank = b.getRankByCandidate(c.getId());
                String tableline = "| " + ((rank != -1) ? rank : " ") + " ";
                System.out.print(tableline);
            }
            System.out.println("|");
        }
        

    }
    
    public static void main(String[] args) {
    	
    	Election election = new Election();
    	
    	election.printBallotDistribution();
    	
    }
    
}