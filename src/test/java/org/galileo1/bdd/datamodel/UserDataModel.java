package  org.galileo1.bdd.datamodel;

public class UserDataModel
{
    private String age;
    private String empStatus;
    private String pir;
    private String balance;
    private String volContrib;
    private String freq;
    private String risk;
    private String goal;
    private String salary;
    private String empContrib;

    public UserDataModel(String age,String empStatus,String pir,String balance,String volContrib
                        ,String freq, String risk, String goal,String salary,String empContrib ) {
        this.setAge(age);
        this.setEmpStatus(empStatus);
        this.setPIR(pir);
        this.setBal(balance);
        this.setVolContrib(volContrib);
        this.setFrequency(freq);
        this.setRisk(risk);
        this.setGoal(goal);
        this.setSalary(salary);
        this.setEmpContrib(empContrib);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
		this.age = age;
	}

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

    public String getPIR(){
        return pir;
    }

    public void setPIR(String pir) {
		this.pir = pir;
	}


    public String getBal() {
        return balance;
    }

    public void setBal(String balance) {
		this.balance = balance;
	}

    public String getVolContib() {
        return volContrib;
    }


    public void setVolContrib(String volContrib) {
		this.volContrib = volContrib;
    }
    
    public String getFrequency() {
        return freq;
    }


    public void setFrequency(String freq) {
		this.freq = freq;
	}

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
		this.risk = risk;
    }
    
    public String getGoal() {
        return goal;
    }


    public void setGoal(String goal) {
		this.goal = goal;
    }
    
    public String getSalary() {
        return salary;
    }


    public void setSalary(String salary) {
		this.salary = salary;
    }
    
    public String getEmpContrib() {
        return empContrib;
    }

    public void setEmpContrib(String empContrib) {
		this.empContrib = empContrib;
    }
    
    @Override
    public String toString() {
        return "TestData{" +
                "age='" + age + '\'' +
                ", empstatus='" + empStatus + '\'' +
                ", annualincome='" + salary + '\'' +
                ", membercontribution=" + empContrib +
                ", pir=" + pir +
                ", kiwisavebalance=" + balance +
                ", contributionamount=" + volContrib +
                ", contributionfrequency=" + freq +
                ", savinggoal=" + goal +
                ", riskprofile=" + risk +
                '}';
    }
}