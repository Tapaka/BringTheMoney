public class Trio {
    private String team1;
    private String team2;
    private String team3;
    private double winPercentage;

    public Trio(String team1, String team2, String team3, double winPercentage) {
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.winPercentage = winPercentage;
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
