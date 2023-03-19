package ru.startandroid.p0451_expandablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String[] groups = new String[] {"HTC", "Sumsung", "LG"};

    private final String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};

    private final String[] htcColor = new String[] {"Red", "Black", "Old", "Green"};

    private final String[] htcPrice = new String[] {"100000", "90000", "80000", "50000"};


    private final String[] phonesSeams = new String[] {"Galaxy s II", "Galaxy nexus", "Wave"};

    private final String[] seamsColor = new String[] {"Red", "Black", "Old"};

    private final String[] seamsPrice = new String[] {"80000", "50000", "30000"};

    private final String[] phonesLG = new String[] {"Optimus", "Optimus link", "Optimus black", "Optimus one"};

    private final String[] lgColor = new String[] {"Red", "Black", "Old", "Green"};

    private final String[] lgPrice = new String[] {"120000", "50000", "40000", "35900"};

    private ArrayList<Map<String, String>> groupData;

    private ArrayList<Map<String, String>> childDataItem;

    private ArrayList<ArrayList<Map<String, String>>> childData;
    private Map<String, String> m;

    private ExpandableListView elvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupData = new ArrayList<>();
        for (String group : groups) {
            m = new HashMap<>();
            m.put("groupName", group);
            groupData.add(m);
        }

        String[] groupForm = new String[]{"groupName"};
        int[] groupTo = new int[] {R.id.text1};

        childData = new ArrayList<>();

        childDataItem = new ArrayList<>();

        for (int i = 0; i < phonesHTC.length; i++) {
            m = new HashMap<>();
            m.put("phoneName", phonesHTC[i]);
            m.put("phoneColor", htcColor[i]);
            m.put("phonePrice", htcPrice[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (int i = 0; i < phonesSeams.length; i++) {
            m = new HashMap<>();
            m.put("phoneName", phonesSeams[i]);
            m.put("phoneColor", seamsColor[i]);
            m.put("phonePrice", seamsPrice[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (int i = 0; i < phonesLG.length; i++) {
            m = new HashMap<>();
            m.put("phoneName", phonesLG[i]);
            m.put("phoneColor", lgColor[i]);
            m.put("phonePrice", lgPrice[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String[] childForm = new String[] {"phoneName", "phoneColor", "phonePrice"};
        int[] childTo = new int[] {R.id.text1, R.id.text2, R.id.text3};



        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                R.layout.group,
                groupForm,
                groupTo,
                childData,
                R.layout.child,
                childForm,
                childTo
        );

        elvMap = findViewById(R.id.elvMap);
        elvMap.setAdapter(adapter);
    }
}