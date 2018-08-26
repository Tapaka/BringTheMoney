import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Letsgo {
    public static void main(String[] args) {
        List<String> ligues = Arrays.asList("d1", "e1", "pl", "l1", "i1");
        long startTime = System.nanoTime();
        for(String ligue : ligues) {
            System.out.println("Ligue : "+ligue);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/football", "root", "");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from " + ligue + " group by HomeTeam");
                List<String> teamList = new ArrayList<>();
                List<Quintor> teamQuintorList = new ArrayList<>();
                while (rs.next()) {
                    teamList.add(escape(rs.getString(4)));
                }
                List<String[]> listeComboEquipe = new ArrayList<>();

                for (String team : teamList) {
                    for (String team2 : teamList) {
                        if (!team2.equals(team)) {
                            for (String team3 : teamList) {
                                if (!team3.equals(team2) && !team3.equals(team)) {
                                    for (String team4 : teamList) {
                                        if (!team4.equals(team) && !team4.equals(team2) && !team4.equals(team3)) {
                                            for (String team5 : teamList) {
                                                if (!team5.equals(team) && !team5.equals(team2) && !team5.equals(team3) && !team5.equals(team4)) {
                                                    List<String> comboEquipe = Arrays.asList(team, team2, team3, team4, team5);
                                                    if (isNewComboEquipe(comboEquipe, listeComboEquipe)) {
                                                        ResultSet rs2 = stmt.executeQuery("SELECT count(Journee) from\n" +
                                                                "(SELECT Journee, count(Journee) > 4 as Quintor FROM football." + ligue + " WHERE\n" +
                                                                "((HomeTeam='" + team + "' and FTR='H' or AwayTeam='" + team + "' and FTR='A')\n" +
                                                                "OR\n" +
                                                                "(HomeTeam='" + team2 + "' and FTR='H' or AwayTeam='" + team2 + "' and FTR='A')\n" +
                                                                "OR\n" +
                                                                "(HomeTeam='" + team3 + "' and FTR='H' or AwayTeam='" + team3 + "' and FTR='A')\n" +
                                                                "OR\n" +
                                                                "(HomeTeam='" + team4 + "' and FTR='H' or AwayTeam='" + team4 + "' and FTR='A')\n" +
                                                                "OR\n" +
                                                                "(HomeTeam='" + team5 + "' and FTR='H' or AwayTeam='" + team5 + "' and FTR='A')\n" +
                                                                ") group by Journee order by Quintor desc) as WinningJournee \n" +
                                                                "where Quintor=1;");
                                                        while (rs2.next()) {
                                                            Double matchWonTogether = Double.valueOf(rs2.getInt(1));
                                                            Double perc = matchWonTogether / ((ligue.equals("d1") ? 34 - (5 * 2 - 2) : 38 - (5 * 2 - 2))) * 100;
                                                            if (!perc.equals(new Double(0))) {
                                                                teamQuintorList.add(new Quintor(team, team2, team3, team4, team5, perc));
                                                            }
                                                        }
                                                        listeComboEquipe.add(new String[]{team, team2, team3, team4, team5});
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                con.close();

                teamQuintorList.sort((Quintor team, Quintor team2) -> Double.compare(team2.getWinPercentage(), team.getWinPercentage()));

                Double dMaxPerc = teamQuintorList.stream().max(Comparator.comparing(Quintor::getWinPercentage)).get().getWinPercentage();
                for (int i = 0; i < teamQuintorList.size(); i++) {
                    if (teamQuintorList.get(i).getWinPercentage() > dMaxPerc - 3)
                        System.out.println(teamQuintorList.get(i).getTeam1() + " / " + teamQuintorList.get(i).getTeam2() + " / " + teamQuintorList.get(i).getTeam3() + " / " + teamQuintorList.get(i).getTeam4() + " / " + teamQuintorList.get(i).getTeam5() + " / " + teamQuintorList.get(i).getWinPercentage());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println(duration/1000000 + " ms");
    }

    private static String escape(String equipe){
        return equipe.replace("'", "\\'");
    }

    private static Boolean isNewComboEquipe(List<String> comboEquipe, List<String[]> listeComboEquipe){
        Boolean isNew = true;
        for(String[] comboExistant : listeComboEquipe){
            Integer matchFound = 0;
            for(String s : comboExistant){
                if(!comboEquipe.contains(s)){
                    break;
                }
                matchFound++;
            }
            if(matchFound==5)return false;
        }
        return isNew;
    }
}
