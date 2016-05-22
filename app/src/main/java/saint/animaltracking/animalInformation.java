package saint.animaltracking;

/**
 * Created by Kodie on 3/30/2016.
 */
public class animalInformation
{

    String shedDate,ate, id, weight;
    //default constructor
    public animalInformation()
    {

    }
    //constructors
    public animalInformation(String id, String weight, String shedDate, String ate)
    {
        this.id = id;
        this.weight = weight;
        this.shedDate = shedDate;
        this.ate = ate;
    }

    //setters
    public void setId(String id)
    {
        this.id = id;
    }
    public void setWeight(String weight)
    {
        this.weight = weight;
    }
    public void setShedDate(String shedDate)
    {
        this.shedDate = shedDate;
    }
    public void setAte(String ate)
    {
        this.ate = ate;
    }

    //getters
    public String getId()
    {
        return this.id;
    }
    public String getWeight()
    {
        return this.weight;
    }
    public String getShedDate()
    {
        return this.shedDate;
    }
    public String getAte() { return this.ate; }

    /*
    Change tables to have an ate table, shed table, and weight table
    with three fields: id, (ate,shed,weight), and a date format;
    Cascading changes to how to feed/clean, displaying this information,
    and database structure
     */
}
