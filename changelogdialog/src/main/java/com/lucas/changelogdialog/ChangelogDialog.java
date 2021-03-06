package com.lucas.changelogdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

public class ChangelogDialog extends DialogFragment {
    private AlertDialog.Builder mBuilder;
    private Context mContext;
    private ReleaseHistory mReleaseHistory;
    private String mPositiveBtnText = "Close";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mContext = context;
    }

    public void createBuilder(Context context) {

        mBuilder = new AlertDialog.Builder(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.changelog_dialog, null, false);
        ListView releaseListView = view.findViewById(R.id.changelog_list);
        releaseListView.setAdapter(getReleaseAdapter());

        mBuilder.setView(view);

        mBuilder.setPositiveButton(mPositiveBtnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dismiss();
            }
        });

        return mBuilder.create();
    }

    public void setCloseBtnText(@NonNull String btnText) {

        mPositiveBtnText = btnText;
    }

    /**
     * Methode um den Titel des ChangelogDialogs anzupassen.
     *
     * @param title ChangelogDialog Title
     */
    public void setTitle(String title) {

        mBuilder.setTitle(title);
    }

    public void setReleaseHistory(ReleaseHistory releaseHistory) {
        mReleaseHistory = releaseHistory;
    }

    private ChangelogAdapter getReleaseAdapter() {

        return new ChangelogAdapter(mReleaseHistory.getHistory(), mContext);
    }
}
