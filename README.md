<h2 align="center">Android MVP 快速集成框架</h2>

##概述

>**baselibrary** 是一个整合了大量主流开源项目的 Android MVP 快速搭建框架, 其中包含 **Retrofit**、**RxJava** 以及 **RxLifecycle**等第三方库, 轻松实现 **MVP + Retrofit + RxJava** 项目.

##框架结构
>见 **file/框架结构.jpg**

##包结构
>见 **file/包结构.jpg**

## Libraries简介
1. [`Mvp` 是 Google 官方出品的 `Mvp` 架构项目，含有多个不同的架构分支.](https://github.com/googlesamples/android-architecture/tree/todo-mvp)
2. [`RxJava` 提供优雅的响应式 Api 解决异步请求以及事件处理.](https://github.com/ReactiveX/RxJava)
4. [`RxAndroid` 为 Android 提供响应式 Api.](https://github.com/ReactiveX/RxAndroid)
5. [`Rxlifecycle`，在 Android 上使用 rxjava 都知道的一个坑，就是生命周期的解除订阅，这个框架通过绑定 Activity 和 Fragment 的生命周期完美解决该问题.](https://github.com/trello/RxLifecycle)
8. [`RxPermissions` 用于处理 Android 运行时权限的响应式库.](https://github.com/tbruyelle/RxPermissions)
9. [`Retrofit` 是 Square 出品的网络请求库，极大的减少了 http 请求的代码和步骤.](https://github.com/square/retrofit)
10. [`Okhttp` 同样Square出品，不多介绍，做Android都应该知道.](https://github.com/square/okhttp)
12. [`Gson` 是 Google 官方的 Json Convert 框架.](https://github.com/google/gson)
13. [`Butterknife` 是 JakeWharton 大神出品的 View 注入框架.](https://github.com/JakeWharton/butterknife)
16. [`Glide` 是本框架默认封装图片加载库，可参照着例子更改为其他的库，Api 和 `Picasso` 差不多，缓存机制比 `Picasso` 复杂，速度快，适合处理大型图片流，支持 gif 图片，`Fresco` 太大了！在 5.0 以下优势很大，5.0 以上系统默认使用的内存管理和 `Fresco` 类似.](https://github.com/bumptech/glide)
17. [`LeakCanary` 是 Square 出品的专门用来检测 `Android` 和 `Java` 的内存泄漏，通过通知栏提示内存泄漏信息.](https://github.com/square/leakcanary)
18. [`Chuck`是用于实时监控应用内HTTP请求数据的框架.](https://github.com/jgilfelt/chuck)
19. [`ARouter`是阿里巴巴出品的路由框架.](https://github.com/alibaba/ARouter)
20. [`AndroidAutoSize`根据今日头条屏幕适配方案写的框架，一个极低成本的 Android 屏幕适配方案.](https://github.com/JessYanCoding/AndroidAutoSize)


##1. config.gradle
本框架提供一个含有大量第三方库的 config.gradle 文件 (里面的所有第三方库并不会全部被引入到项目中, 只是作为变量方便项目中多个位置进行引用, 特别适用于多 Module 的项目), 用于第三方库的版本管理,

>首先加入引用

	def config = rootProject.ext.ver
	def library = rootProject.ext.library

>然后就可以添加配置版本信息,如

	versionCode config.versionCode
	versionName config.versionName

>添加依赖,如

	annotationProcessor library.butterknife_compiler
    implementation library.Arouter_api
    annotationProcessor library.Arouter_compiler

##2.使用Lambda
本框架使用了使用 Lambda 表达式

	compileOptions {
        targetCompatibility 1.8
        sourceCompatibility 1.8
    }


##3.Application
如果要自定义Application类, 需要继承BaseApplication,已实现

      1.监听所有activity的生命周期
      2.内存泄漏检测初始化
      3.获取app单例
      4.Logger初始化
      5.Arouter初始化

##4.MVP实战
###4.1 ui包的分包
demo中使用的分包方式是

	ui包
	--模块1包
		--页面1包
			--页面1的contract
			--页面1的activity/fragment
			--页面1的presenter
			--页面1的内部fragment的mvp包
				-- ...
		--页面2包
			...
	--模块2包
		--页面
			--...


###4.2 Contract
主要用于定义view和presenter的接口,如下:

	public interface UserInfoContract {
	    interface Display extends IBaseDisplay {
	        void showUserInfo(UserInfoBean data);
	        void showShopInfo(ShopInfoBean data);
	    }
	
	    interface Presenter extends IBasePresenter<Display> {
	        void getUserInfo(String token);
	        void getShopInfo(String storeCode);
	    }
	}


##4.3 View层
目前主要是继承baseActivity或者baseFragment, 并且实现View接口,实现View层

	public class UserInfoFragment extends BaseFragment<UserInfoContract.Presenter> implements UserInfoContract.Display {
	   
	}


##4.4 Presenter层
继承Basepresenter, 并且实现Presenter接口, 实现Presenter层

	public class UserInfoPresenter extends BasePresenter<UserInfoContract.Display> implements UserInfoContract.Presenter {
	
	}


##4.5 Model层
Model有接口与实现,首先定义接口如下:

	public interface UserModelInterface {
	    Observable<BaseBean<LoginResultBean>> loginPhone(String phone, String pw, String storeCode);
	
	    Observable<BaseBean<UserInfoBean>> userInfo(String token);
	
	}

再实现接口如下:


	public class UserModelImpl implements UserModelInterface {
	    @Override
	    public Observable<BaseBean<LoginResultBean>> loginPhone(String phone, String pw, String storeCode) {
	        HashMap<String, String> body = new HashMap<>();
	        body.put("vtrId", "f6a9027f4e9d4c2019f28b55983db501");
	        body.put("username", phone);
	        body.put("password", pw);
	        body.put("urlCode", storeCode);
	        body.put("verifyCode", "");
	
	        return RetrofitClient.getAPIService(UserService.class)
	                .loginPhone(body);
	    }
	
	    @Override
	    public Observable<BaseBean<UserInfoBean>> userInfo(String token) {
	        HashMap<String, String> body = new HashMap<>();
	        body.put("token", token);
	
	        return RetrofitClient.getAPIService(UserService.class)
	                .userInfo(body);
	    }
	}

\* **需要注意的地方是: Model层是各个页面共用的.这样的好处是,同样的接口请求,只需要写一次.Presenter可以自由获取一个或者多个Model实例.**



#5. Rxjava与Activity和Fragment的生命周期绑定
在BaseActivity和BaseFragment中使用**Rxlifecycle**进行与Rxjava的绑定,实现在activity或fragment在ondestroy以后自动取消rxjava订阅,如下:

	mLifecycleProvider = AndroidLifecycle.createLifecycleProvider(this);

在掉取网络的时候绑定

        observable..compose(mView.getLifecycleProvider().bindUntilEvent(Lifecycle.Event.ON_DESTROY))



#6. Activity栈管理
主要采用的方式是在Application中监听所以Activity的生命周期:

	/**
	 * activity生命周期监听类
	 */
	public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            ActivityManager.addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ActivityManager.removehActivity(activity);
        }
    }

---

	/**
	 * Created by xieguofeng on 2018/8/21
	 * activity栈管理类
	 */
	public class ActivityManager {
	    private static Stack<Activity> mActivityStack = new Stack<>(); //activity栈
	
	    /**
	     * 添加一个Activity到栈
	     * @param activity
	     */
	    public static void addActivity(Activity activity) {
	        mActivityStack.add(activity);
	    }
	
	    /**
	     * 从activity栈去除一个activity
	     * @param activity
	     */
	    public static void removehActivity(Activity activity) {
	        mActivityStack.remove(activity);
	    }
	
	    /**
	     * 获取activity栈
	     * @return
	     */
	    public static Stack<Activity> getAllActivity() {
	        return mActivityStack;
	    }
	
	    /**
	     * 获取activity栈顶的activity
	     * @return
	     */
	    public static Activity getTopActivity() {
	        if (mActivityStack.size() == 0) {
	            return null;
	        } else {
	            return mActivityStack.peek();
	        }
	    }
	}


#7. 权限请求
使用了rxPermition封装了权限请求工具类PermisionUtil,只要调用showTipsDialog方法即可.

#8. 添加toolbar
自定义了toolbar封装成CommonToolbar,已在baseActivity集成,只需要重写方法setToolbar,如下:

    @Override
    protected CommonToolbar setToolbar() {
        return new CommonToolbar.Builder().setTitle("登录").build(this);
    }


#9. kotlin使用
此框架兼容kotlin,可以参考demo的loginKotlin页面

#10. 使用路由Arouter
在 constant包下RouterPath类,可以定义路由路径. 路径就是一个唯一的常量. 每个activity都指定一个的路由路径.
使用路由的好处主要体现在:

1. 页面管理更加直观
2. 可以自动获取传递的参数
3. 使用路由后,此框架可以支持组件化,只要把module建好就可以,后期可以写一个组件化的demo

##11. 内存泄漏监控
采用了`LeakCanary`框架进行内存泄漏的监控,如果出现内存泄漏,就会再通知栏出现通知信息,根据通知信息可以找到内存泄漏的源头.

##12. HTTP请求信息捕获
1. 目前有两种方式捕获到HTTP请求的信息,一种是日志,通过Logger进行打印log,显示请求的url,请求参数,返回的json信息.
2. 采用chuck框架,在任何请求信息,都会在通知栏进行通知,点开通知就可以查看详细的HTTP请求的信息,还可以复制

##13. SharedPreferences管理
SP的管理方式是:

1. 先定义常量,作为SP的key的名字
2. 定义对应的key的get和set方法,这样好处是固定这个key的数据类型,以后调用也很方便


		public class SP {
		    public static final String LANGUAGE = "language"; //语言 0:中文, 1:英文
		    public static final String IS_FIRST_LUNCH = "is_first_lunch"; //是否第一次启动
		    public static final String TOKEN = "token";
		
		    public static void setLanguage(int language) {
		        SPUtil.put(LANGUAGE, language);
		    }
		
		    public static int getLanguage() {
		        return (int) SPUtil.get(LANGUAGE, 0);
		    }
		
		    public static void setFirstLunch(boolean isFirstLaunch) {
		        SPUtil.put(IS_FIRST_LUNCH, isFirstLaunch);
		    }
		
		    public static boolean getFirstLunch() {
		        return (boolean) SPUtil.get(IS_FIRST_LUNCH, false);
		    }
		
		    public static void setToken(String token) {
		        SPUtil.put(TOKEN, token);
		    }
		
		    public static String getToken() {
		        return (String) SPUtil.get(TOKEN, "");
		    }
		}


#14. 屏幕适配方案
采用今日头条屏幕适配方案的第三方库`AndroidAutoSize`.这种方案耦合度比较小.目前来看兼容性比较好.不过后期需要阅读源码和测试兼容性.

#15. 环境切换
在demo中添加了环境切换的功能，方便开发调试。主要的类是 SwitchUrlActivity，EnvConfigUtils

- 首先，要在build.gradle中配置环境；
- 然后，在EnvConfigUtils里面，配置好各个环境对应的变量值
- 然后，根据环境和变量值，修改SwitchUrlActivity和布局文件
- 页面中添加一个隐藏入口，进入SwitchUrlActivity

#16. ConfigModule配置
由于框架中，有些操作需要由用户决定，在ConfigModule中把这些操作抽象出来，由用户去实现

- 首先，定义一个类，继承ConfigModule, 实现里面的所有方法
- 在清单文件中定义meta-data, 只想该类，如：

		<meta-data
		        android:name="com.mhace.mvpdemo.GlobalConfiguration"
		        android:value="ConfigModule"/>


#17. 网络请求
网络请求使用了retrofit+rxjava的形式，

- 在包com.mhace.baselibrary.network.transformer下，有各种转换器，可供使用
- RxCallback对观察者进行了封装
- RetrofitClient是对refrefot的初始化
- RequestParameter是对请求参数的封装，加入公共请求参数，对请求参数进行加密, 配合ConfigModule使用。















