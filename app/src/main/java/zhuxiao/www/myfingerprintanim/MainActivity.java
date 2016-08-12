package zhuxiao.www.myfingerprintanim;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.swir.fingerprint.view.FingerPrintView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
    @Bind(R.id.swirl)
    FingerPrintView mFpView;
    @Bind(R.id.state) RadioGroup stateView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        stateView.setOnCheckedChangeListener(this);
    }

    @Override public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.defaults:
                mFpView.setState(FingerPrintView.State.DEFAULT);
                break;
            case R.id.success:
                mFpView.setState(FingerPrintView.State.SUCCESS);
                break;
            case R.id.fail:
                mFpView.setState(FingerPrintView.State.FAIL);
                break;
            default:
                throw new IllegalArgumentException("Unexpected checkedId: " + checkedId);
        }
    }
}
