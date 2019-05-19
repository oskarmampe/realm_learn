package info.adavis.topsy.turvey.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RecipeStep extends RealmObject
{
    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private int stepNumber;

    private String instruction;

    public RecipeStep()
    {
    }

    public RecipeStep(int stepNumber, String instruction)
    {
        this.stepNumber = stepNumber;
        this.instruction = instruction;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public int getStepNumber()
    {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber)
    {
        this.stepNumber = stepNumber;
    }

    public String getInstruction()
    {
        return instruction;
    }

    public void setInstruction(String instruction)
    {
        this.instruction = instruction;
    }

    @Override
    public String toString()
    {
        return "RecipeStep{" +
                "id='" + id + '\'' +
                ", stepNumber=" + stepNumber +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
