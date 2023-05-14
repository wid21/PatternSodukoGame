
public class ProfileDatabase {
    private static String obj;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private ProfileDatabase() {}

    public static String getInstance()
    {
        if (obj==null)
            obj = "src/Profiles.txt";
        return obj;
    }
}
