package se.wibi.observer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HelpDialogFragment extends DialogFragment {
	 private static final String KEY_SAVE_RATING_BAR_VALUE = "KEY_SAVE_RATING_BAR_VALUE";
	 
	 public static HelpDialogFragment newInstance() {
		 HelpDialogFragment frag = new HelpDialogFragment();
         return frag;
     }
	 @Override
     public Dialog onCreateDialog(Bundle savedInstanceState) {
         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
         View view = getActivity().getLayoutInflater().inflate(R.layout.help_dialog, null);
         alertDialogBuilder.setView(view);
         alertDialogBuilder.setTitle(getString(R.string.dialog_title));
         alertDialogBuilder.setMessage(R.string.dialog_message);
         alertDialogBuilder.setPositiveButton("OK", null);
         return alertDialogBuilder.create();
     }

     @Override
     public void onSaveInstanceState(Bundle outState) {
        
         super.onSaveInstanceState(outState);
     }
 }