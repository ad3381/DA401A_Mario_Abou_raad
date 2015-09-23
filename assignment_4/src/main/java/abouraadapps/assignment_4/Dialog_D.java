package abouraadapps.assignment_4;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog_D extends DialogFragment implements Dialog.OnClickListener {


    public Dialog_D() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String a = getString(R.string.d_alt_one);
        String b = getString(R.string.d_alt_two);
        String c = getString(R.string.d_alt_three);

        String[] values = {a, b, c};

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.question_d)
                .setItems(values, this);

        return dialog.create();

    }


    public void onClick(DialogInterface dialog, int which) {
        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        MediaPlayer right = MediaPlayer.create(getContext(), R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(getContext(), R.raw.fail);
        switch (which) {
            case 0:
                Toast.makeText(getContext(), "Wrong answer =(", Toast.LENGTH_SHORT).show();
                wrong.start();
                break;
            case 1:
                Toast.makeText(getContext(), "Wrong answer =(", Toast.LENGTH_SHORT).show();
                wrong.start();
                break;
            case 2:
                Toast.makeText(getContext(), "Correct answer! =)", Toast.LENGTH_SHORT).show();
                right.start();
                vibe.vibrate(200);
                break;
        }
    }
}
