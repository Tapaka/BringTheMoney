public class Duo {
    private String team1;
    private String team2;
    private double winPercentage;

    public Duo(String team1, String team2, double winPercentage) {
        this.team1 = team1;
        this.team2 = team2;
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

    public double getWinPercentage() {
        return winPercentage;
    }

    public void setWinPercentage(double winPercentage) {
        this.winPercentage = winPercentage;
    }
}
