package model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.exception.InvalidDataException;

public class Project implements Serializable {

	private static final long serialVersionUID = 2180069907986538519L;

	private String acronym;
	private String description;
	private int fundingDuration;
	private int budget;
	private Date created;
	private Owner owner;
	private String category;
	private List<Evaluation> evaluations;
	private List<Document> documents;

	public Project() {
	}
	
	public Project(String acronym, String description, //int fundingDuration,
			int budget, Owner owner, String category) throws InvalidDataException {
		setAcronym(acronym);
		setDescription(description);
		setFundingDuration(100);
		setBudget(budget);
		setCreated(new Date());
		setOwner(owner);
		setCategory(category);
		setEvaluations(new LinkedList<Evaluation>());
		setDocuments(new LinkedList<Document>());
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFundingDuration() {
		return fundingDuration;
	}

	public void setFundingDuration(int fundingDuration) throws InvalidDataException {
		if(fundingDuration <= 0) {
			throw new InvalidDataException("Funding must be specified");
		}				
		this.fundingDuration = fundingDuration;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void addEvaluation(Evaluation eval) {
		evaluations.add(eval);
		eval.setProject(this);
	}

	public List<Evaluation> getEvaluations() {
		return evaluations;
	}
	
	public void addDocument(Document doc) {
		documents.add(doc);
	}
	
	public List<Document> getDocuments() {
		return documents;
	}
	
	// project title check
	public boolean isValidProjectTitle(String projTitle) {
		if ( projTitle.equals("") || projTitle == null )
			return false;
		else
			return true;
	}
		
	// project description check
	public boolean isValidProjectDescription(String projDescription) {
		if ( projDescription.equals("") || projDescription == null )
			return false;
		else
			return true;
	}
	
	// project budget check
	public boolean isValidProjectBudget(String projBudget) {
		if ( projBudget.equals("") || projBudget == null ) {
			return false;
		} else {
			try {
				Integer.parseUnsignedInt(projBudget);
			} catch (NumberFormatException e) {
				return false;
			}
			return true;
		}
	}
	
	// display budget
	public String toStringBudget() {
		DecimalFormat df = new DecimalFormat(",###");
		String strBudget = df.format(this.budget);
		strBudget = strBudget.replace(",", ".");
		return strBudget;
	}

}
