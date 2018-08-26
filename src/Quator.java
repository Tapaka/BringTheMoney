public class Quator {
    private String team1;
    private String team2;
    private String team3;
    private String team4;
    private double winPercentage;

    public Quator(String team1, String team2, String team3, String team4, double winPercentage) {
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.winPercentage = winPercentage;
    }

    public String getTeam4() {
        return team4;
    }

    public void setTeam4(String team4) {
        this.team4 = team4;
    }

    public String getTeam1() {

        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam3() {
        return team3;
    }

    public void setTeam3(String team3) {
        this.team3 = team3;
    }

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }
}
