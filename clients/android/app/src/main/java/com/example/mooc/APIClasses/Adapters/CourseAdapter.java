package com.example.mooc.APIClasses.Adapters;

import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.CourseExecise;
import com.example.mooc.Entities.CourseLesson;
import com.example.mooc.Entities.CourseStream;

import com.google.gson.Gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CourseAdapter implements JsonDeserializer<Course> {

    private static final String TYPE_FIELD = "type";
    private static final String NAME_FIELD = "id";

    private static final String LESSON_TYPE   = "1";
    private static final String EXERCISE_TYPE = "2";
    private static final String STREAM_TYPE  = "3";



    @Override
    public Course deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject() && json.getAsJsonObject().has(TYPE_FIELD))
        {
            JsonObject jsonObject = json.getAsJsonObject();
            final String type = jsonObject.get(TYPE_FIELD).getAsJsonObject().get(NAME_FIELD).getAsString();

            switch (type)
            {
                case LESSON_TYPE:
                    return context.deserialize(json, CourseLesson.class);

                case EXERCISE_TYPE:
                    return context.deserialize(json, CourseExecise.class);

                case STREAM_TYPE:
                    return context.deserialize(json, CourseStream.class);


                default:
                    return new Gson().fromJson(json, typeOfT);
            }
        }
        else
        {
            return null;
        }
    }

}