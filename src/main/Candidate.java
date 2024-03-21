package main;

public class Candidate {
	
	private int idNumber2;
	private String candidateName;
	private boolean active;
	
	/* Creates a Candidate from the line. The line will have the format
	ID#,candidate_name . */
	public Candidate(String line) {
		
		String[] parts = line.split(",");
		
		this.idNumber2 = Integer.parseInt(parts[0]);
		
		this.candidateName = parts[1];
		
		this.active = true;
		
	}
	
	
	
	// returns the candidate’s id
	public int getId() {
		
		return this.idNumber2;
		
	}
	
	// Whether the candidate is still active in the election
	public boolean isActive() {
		
		return active;
		
	}
	
	
	
	// return the candidates name
	public String getName() {
		
		return this.candidateName;
	}

}
