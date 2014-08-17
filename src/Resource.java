//***** CLASS: Resource.java
// PURPOSE
//  * Contains constructor, access, mutator, and other methods for a Resource object
// RESULTS
//  * None visible to user
// NOTES
//  * Called by ResourceManager.java
//  * Does NOT contain main program (public static void main( String [] args))
// HISTORY
//  * Created by SEK 08/09/2014
// SOURCE
class Resource
{
    // Private fields specific to a Resource
    private String name = "";
    private String PID = "";
    private String sessionName = "";
    private String sessionNum = "";
    private Integer memUsage = 0;
    private String status = "";
    private String userName= "";
    private String cpuTime = "00:00:00";
    private String windowTitle = "";

    //***** CLASS: Resource.java / METHOD: Resource() / METHOD TYPE: Constructor
    // PURPOSE
    //  * Constructor method for a Resource object
    // PARAMETERS
    //  * name (string) -
    //  * PID (string) -
    //  * sessionName (string) -
    //  * sessionNum (string) -
    //  * memUsage (int) -
    //  * status (string) -
    //  * userName (string) -
    //  * cpuTime (string) -
    //  * windowTitle (String) -
    // RESULT
    //  * Creates a Resource object
    // HISTORY
    //  * Created by SEK 08/09/2014
    //  * Edited by SEK 08/10/2014 - Added more fields
    // SOURCE
    public void create()
    {
        this.name = "";
        this.PID = "";
        this.sessionName = "";
        this.sessionNum = "";
        this.memUsage = 0;
        this.status = "";
        this.userName = "";
        this.cpuTime = "00:00:00";
        this.windowTitle = "";
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getName() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the name of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public String getName()
    {
        return name;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getPID() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the PID of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public String getPID()
    {
        return PID;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getSessionName() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the session name of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public String getSessionName()
    {
        return sessionName;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getSessionNum() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the session number of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public String getSessionNum()
    {
        return sessionNum;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getMemUsage() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the memory usage of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns an integer value
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public Integer getMemUsage()
    {
        return memUsage;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getStatus() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the status of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public String getStatus()
    {
        return status;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getUserName() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the user name of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public String getUserName()
    {
        return userName;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getCPUTime() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the CPU time of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public String getCPUTime()
    {
        return cpuTime;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: getWindowTitle() / METHOD TYPE: Accessor
    // PURPOSE
    //  * Accessor method for the window title of a Resource
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Returns a string value
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public String getWindowTitle()
    {
        return windowTitle;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setName() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the name of a Resource
    // PARAMETERS
    //  * strName (string) - the name of the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public void setName(String strName)
    {
        this.name = strName;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setPID() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the PID of a Resource
    // PARAMETERS
    //  * strPID (string) - the PID of the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public void setPID(String strPID)
    {
        this.PID = strPID;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setSessionName() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the session name of a Resource
    // PARAMETERS
    //  * strSessionName (string) - the session name for the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public void setSessionName(String strSessionName)
    {
        this.sessionName = strSessionName;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setSessionNumber() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the session number of a Resource
    // PARAMETERS
    //  * strSessionNum (string) - the session number for the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public void setSessionNum(String strSessionNum)
    {
        this.sessionNum = strSessionNum;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setStatus() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the memory usage of a Resource
    // PARAMETERS
    //  * intMemUsage (int) - the memory usage of the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public void setMemUsage(Integer intMemUsage)
    {
        this.memUsage = intMemUsage;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setStatus() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the status of a Resource
    // PARAMETERS
    //  * strStatus (string) - the status of the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public void setStatus(String strStatus)
    {
        this.status = strStatus;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setUserName() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the user name of a Resource
    // PARAMETERS
    //  * strUserName (string) - the user name for the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public void setUserName(String strUserName)
    {
        this.userName = strUserName;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setCPUTime() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the CPU time of a Resource
    // PARAMETERS
    //  * strCPUTime (string) - the CPU time of a the Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public void setCPUTime(String strCPUTime)
    {
        this.cpuTime = strCPUTime;
    }
    //*****

    //***** CLASS: Resource.java / METHOD: setWindowTitle() / METHOD TYPE: Mutator
    // PURPOSE
    //  * Mutator method for the window title of a Resource
    // PARAMETERS
    //  * strWindowTitle (string) - the Window Title for a Resource
    // RESULT
    //  * NONE
    // HISTORY
    //  * Created by SEK 08/10/2014
    // SOURCE
    public void setWindowTitle(String strWindowTitle)
    {
        this.windowTitle = strWindowTitle;
    }
    //*****

    @Override
    public String toString()
    {
        return "Resource{Name=" + name +
                ", PID=" + PID +
                ", Session Name=" + sessionName +
                ", Session Num=" + sessionNum +
                ", Mem Usage=" + memUsage +
                ", Status=" + status +
                ", User Name=" + userName +
                ", CPU Time=" + cpuTime +
                ", Window Title=" + windowTitle +
                '}';
    }
}
//*****