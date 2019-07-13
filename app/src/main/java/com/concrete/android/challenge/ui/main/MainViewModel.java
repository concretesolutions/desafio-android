package com.concrete.android.challenge.ui.main;

import androidx.databinding.ObservableField;
import com.concrete.android.challenge.data.remote.GithubService;
import com.concrete.android.challenge.ui.base.BaseViewModel;
import com.concrete.android.challenge.utils.rx.SchedulerProvider;

/**
 * @author Thiago Corredo
 * @since 2019-05-28
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

  private final ObservableField<String> imageUrl = new ObservableField<>();
  private final ObservableField<String> userName = new ObservableField<>();
  private final ObservableField<String> userEmail = new ObservableField<>();

  public MainViewModel(GithubService service, SchedulerProvider schedulerProvider) {
    super(service, schedulerProvider);
  }

  public void onNavMenuCreated() {
    imageUrl.set(
        "https://scontent.fmii1-1.fna.fbcdn.net/v/t1.0-1/c1.0.160.160a/p160x160/40421240_2156605427747081_4388806640585932800_n.jpg?_nc_cat=104&_nc_ht=scontent.fmii1-1.fna&oh=ea1a4e68453ecddde16069369b064db7&oe=5D5A2E6C");
    userName.set("Thiago Corredo Soares");
    userEmail.set("thiagocorredo@gmail.com");
  }

  public ObservableField<String> getImageUrl() {
    return imageUrl;
  }

  public ObservableField<String> getUserName() {
    return userName;
  }

  public ObservableField<String> getUserEmail() {
    return userEmail;
  }
}
