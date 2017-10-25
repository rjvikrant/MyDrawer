package via.vik.mydrawer.fragments.PartnerPreferences;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import via.vik.mydrawer.MainActivity;
import via.vik.mydrawer.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyPreferencesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyPreferencesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPreferencesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnChildFragmentInteractionListener mParentListener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText edt_fname,edt_lname;
    Boolean flag;

    private OnFragmentInteractionListener mListener;

    public MyPreferencesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPreferencesFragment newInstance(String param1, String param2) {
        MyPreferencesFragment fragment = new MyPreferencesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.e("load >>>","loading chat fragmnet");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

            View rootView=inflater.inflate(R.layout.fragment_my_preferences, container, false);

                flag=true;

        edt_fname=(EditText)rootView.findViewById(R.id.edt_name);
       // edt_lname=(EditText) rootView.findViewById(R.id.edt_last_name);


        ((MainActivity) getActivity()).passVal(new MainActivity.FragmentCommunicator() {
            @Override
            public void passData(String name) {
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();

                if (flag) {
                    edt_fname.setEnabled(true);
                  //  edt_lname.setEnabled(true);
                    flag=false;
                }else {
                    edt_fname.setEnabled(false);
                //    edt_lname.setEnabled(false);

                    Log.e("Text is >>",""+edt_fname.getText().toString());
                    flag=true;
                }
            }
        });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public interface OnChildFragmentInteractionListener {
        void messageFromChildToParent(String myString);
    }

}
