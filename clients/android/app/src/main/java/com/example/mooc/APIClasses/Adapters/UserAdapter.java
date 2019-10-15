package com.example.mooc.APIClasses.Adapters;

import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserAdmin;
import com.example.mooc.Entities.UserParent;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Entities.UserTeacher;

import com.google.gson.Gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserAdapter implements JsonDeserializer<User> {

    private static final String TYPE_FIELD = "type";
    private static final String NAME_FIELD = "id";

    private static final String ADMIN_TYPE   = "1";
    private static final String TEACHER_TYPE = "2";
    private static final String PARENT_TYPE  = "3";
    private static final String CHILD_TYPE   = "4";


    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject() && json.getAsJsonObject().has(TYPE_FIELD))
        {
            JsonObject jsonObject = json.getAsJsonObject();
            final String type = jsonObject.get(TYPE_FIELD).getAsJsonObject().get(NAME_FIELD).getAsString();

            switch (type)
            {
                case ADMIN_TYPE:
                    return context.deserialize(json, UserAdmin.class);

                case TEACHER_TYPE:
                    return context.deserialize(json, UserTeacher.class);

                case PARENT_TYPE:
                    return context.deserialize(json, UserParent.class);

                case CHILD_TYPE:
                    return context.deserialize(json, UserStudent.class);

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