package com.englishnary.eridev.android.englishnary;
//http://www.hermosaprogramacion.com/2015/01/android-json-parsing/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DefinitionFragment extends Fragment {
    private static final String LOG_TAG = DefinitionFragment.class.getSimpleName();
    private static final int DEFINITIONS_LOADER = 0;
    private DefinitionsAdapter mDefinitionsAdapter;

    Button btnBuscar;
    FetchDefinitionTask definitionTask;

    public DefinitionFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

    }

    // Inflate the menu; this adds items to the action bar if it is present.
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_definition_fragment, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //If item action_refresh clik call FetchDefinitionTask to execute
        int id = item.getItemId();
        //Toast.makeText(DefinitionFragment.this, "Awesome you are pushed button ok", Toast.LENGTH_SHORT).show();
        if (id == R.id.action_refresh) {
            //definitionTask.execute("Activity");

            return true;
        }
        Log.v(LOG_TAG, "Action refresh is selected" + id);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);

        //Inflate root fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lista = (ListView) rootView.findViewById(R.id.listview_definitions);

        //UI
//        btnBuscar = (Button)rootView.findViewById(R.id.btnBuscar);

        // Create data for the ListView.
        String[] wordsArray = {
                "Access", "Account", "Activity", "Administrative", "Advantage",
                "Advertisements", "Animate", "Applications", "Art", "Attachment",
                "Audience", "Admin",
                "Back up", "Bandwidth", "Banner", "Basics", "Benefit", "Blog", "Blue tooth",
                "Bookmarks", "Boot up", "Broadband", "Browser", "Bugs", "Bytes"
        };

        mDefinitionsAdapter = new DefinitionsAdapter(getActivity(), new ArrayList<Definitions>());
        lista.setAdapter(mDefinitionsAdapter);

        definitionTask = new FetchDefinitionTask(getContext(), mDefinitionsAdapter);
        definitionTask.execute();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Definitions definitionDataInfo = mDefinitionsAdapter.getItem(position);
        Toast.makeText(getActivity(), "Awesome you are pushed definition " + definitionDataInfo,
                Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getActivity(), NotesFecha.class)
//                .putExtra(Intent.EXTRA_TEXT, (Parcelable) definitionDataInfo);
//        startActivity(intent);

//                Intent intent = new Intent(getActivity(), DetailDefActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT, String.valueOf(definitionDataInfo));
//                startActivity(intent);
            }
        });


        return rootView;
    }




   }


