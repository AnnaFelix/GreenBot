package lu.uni.bicslab.greenbot.android.ui.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import lu.uni.bicslab.greenbot.android.R;
import lu.uni.bicslab.greenbot.android.ui.activity.fromprofile.ProductConsultActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.fromprofile.ProductsScannerActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.fromprofile.RecompensesActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.fromprofile.RetourClientActivity;
import lu.uni.bicslab.greenbot.android.ui.activity.fromprofile.TermesAndConditionActivity;

public class ProfileFragment extends Fragment {

    private ProfileViewModel homeViewModel;
    RelativeLayout layout_produitsscanner, layout_produitsconsultes, layout_retourclient, layout_recompenses, layout_teams;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        layout_produitsscanner = (RelativeLayout)root.findViewById(R.id.layout_produitsscanner);
        layout_produitsconsultes = (RelativeLayout)root.findViewById(R.id.layout_produitsconsultes);
        layout_retourclient = (RelativeLayout)root.findViewById(R.id.layout_retourclient);
        layout_recompenses = (RelativeLayout)root.findViewById(R.id.layout_recompenses);
        layout_teams = (RelativeLayout)root.findViewById(R.id.layout_teams);

        layout_produitsscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductsScannerActivity.class);
                startActivity(intent);
            }
        });
        layout_produitsconsultes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ProductConsultActivity.class);
                        startActivity(intent);
                    }
                });

        layout_retourclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RetourClientActivity.class);
                startActivity(intent);
            }
        });
        layout_recompenses.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), RecompensesActivity.class);
                        startActivity(intent);
                    }
                });
        layout_teams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TermesAndConditionActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
