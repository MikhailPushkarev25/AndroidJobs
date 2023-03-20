package ru.startandroid.p0461_expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AndroidHelper {

    private static final String ATTR_GROUP_NAME = "groupName";
    private static final String ATTR_PHONE_NAME = "phoneName";

    private static final String[] GROUP = new String[] {"HTC", "Sumsung", "LG"};

    private static final String[] ELEMENT_HTC = new String[] {"Disire", "Smart G", "Titan"};
    private static final String[] ELEMENT_SUMSUNG = new String[] {"Galaxy", "Galaxy note", "Revolete", "I5"};
    private static final String[] ELEMENT_LG = new String[] {"PH", "Come3", "somTable", "Execute"};

    Context cx;

    SimpleExpandableListAdapter adapter;

    ArrayList<Map<String, String>> groupData;
    ArrayList<Map<String, String>> element;
    ArrayList<ArrayList<Map<String, String>>> sumArray;
    Map<String, String> put;

    public AndroidHelper(Context _cx) {
        this.cx = _cx;
    }

    SimpleExpandableListAdapter getAdapter() {
        groupData = new ArrayList<>();
        for (String groups : GROUP) {
            put = new HashMap<>();
            put.put(ATTR_GROUP_NAME, groups);
            groupData.add(put);
        }

        String[] groupPost = new String[] {ATTR_GROUP_NAME};
        int[] groupPostIn = new int[] {android.R.id.text1};



        element = new ArrayList<>();
        sumArray = new ArrayList<>();
        for (String htc : ELEMENT_HTC) {
            put = new HashMap<>();
            put.put(ATTR_PHONE_NAME, htc);
            element.add(put);
        }

        sumArray.add(element);

        element = new ArrayList<>();
        for (String sumsung : ELEMENT_SUMSUNG) {
            put = new HashMap<>();
            put.put(ATTR_PHONE_NAME, sumsung);
            element.add(put);
        }

        sumArray.add(element);

        element = new ArrayList<>();
        for (String lg : ELEMENT_LG) {
            put = new HashMap<>();
            put.put(ATTR_PHONE_NAME, lg);
            element.add(put);
        }

        sumArray.add(element);

        String[] elementPost = new String[] {ATTR_PHONE_NAME};
        int[] elementPostIn = new int[] {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                cx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupPost,
                groupPostIn,
                sumArray,
                android.R.layout.simple_list_item_1,
                elementPost,
                elementPostIn);

        return adapter;
    }

    public String getGroupText(int group) {
        return ((Map<String, String>)(adapter.getGroup(group))).get(ATTR_GROUP_NAME);
    }

    public String getElementText(int group, int element) {
        return ((Map<String, String>)(adapter.getChild(group, element))).get(ATTR_PHONE_NAME);
    }

    public String getGroupElement(int group, int element) {
        return getGroupText(group) + " " +getElementText(group, element);
    }
}
