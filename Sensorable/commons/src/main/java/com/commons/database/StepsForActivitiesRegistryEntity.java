package com.commons.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = ActivityEntity.class,
                parentColumns = "id",
                childColumns = "id_activity",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = ActivityStepEntity.class,
                parentColumns = "id",
                childColumns = "id_step",
                onDelete = ForeignKey.CASCADE)
})
public class StepsForActivitiesRegistryEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "id_activity")
    public long idActivity;

    @NonNull
    @ColumnInfo(name = "id_step")
    public int idStep;


    @NonNull
    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "user_id")
    public String userId;

    public StepsForActivitiesRegistryEntity(@NonNull long idActivity, int idStep, @NonNull long timestamp, @NonNull String userId) {
        this.idActivity = idActivity;
        this.idStep = idStep;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public String toJson() {
        return "[" + idActivity + "," + idStep + "," + timestamp + ", \"" + userId + "\" ]";
    }
}