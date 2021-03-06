package samcorp.newsapp;

/**
 * Created by samwyz on 7/18/16.
 */
public class Constants {

    public static final String GUARDIAN_FILM =
            "http://content.guardianapis.com/search?order-by=newest&section=film&limit=5&format=json&show-fields=thumbnail%2CtrailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String GUARDIAN_WORLD =
            "http://content.guardianapis.com/search?order-by=newest&section=world&limit=5&format=json&show-fields=thumbnail%2CtrailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String GUARDIAN_US =
            "http://content.guardianapis.com/search?order-by=newest&section=us-news&limit=5&format=json&show-fields=thumbnail%2CtrailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String GUARDIAN_TRAVEL =
            "http://content.guardianapis.com/search?order-by=newest&section=travel&limit=5&format=json&show-fields=thumbnail%2CtrailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String GUARDIAN_SPORTS =
            "http://content.guardianapis.com/search?order-by=newest&section=sport&limit=5&format=json&show-fields=trailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String GUARDIAN_TECH =
            "http://content.guardianapis.com/search?order-by=newest&section=technology&limit=5&format=json&show-fields=thumbnail%2CtrailText&api-key=1caa0efc-fb6b-4b50-a2d9-20aa44d13a81";

    public static final String NYT_US =
            "https://api.nytimes.com/svc/topstories/v2/national.json?limit=5&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_WORLD =
            "https://api.nytimes.com/svc/topstories/v2/world.json?limit=5&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_SPORTS =
            "https://api.nytimes.com/svc/topstories/v2/sports.json?page=0&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_OPINION =
            "https://api.nytimes.com/svc/topstories/v2/opinion.json?page=0&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_TECH =
            "https://api.nytimes.com/svc/topstories/v2/technology.json?page=0&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_MOVIES =
            "https://api.nytimes.com/svc/topstories/v2/movies.json?page=0&api-key=7ee973aaf5ba44d392b221269981c34b";

    public static final String NYT_TRAVEL =
            "https://api.nytimes.com/svc/topstories/v2/travel.json?page=0&api-key=7ee973aaf5ba44d392b221269981c34b";

}
