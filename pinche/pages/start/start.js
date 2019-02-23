//login.js
//获取应用实例
var app = getApp();
Page({
  data: {
    remind: '加载中',
    angle: 0,
    userInfo: {},
    hasUserInfo: false,
    gourl: '/pages/setpersoninfo/setpersoninfo',
    tiaozhuan:true,
    buttonClicked: true
  },
  getUserInfo(info) {
    const userInfo = info.detail.userInfo
    this.setData({
      userInfo,
      hasUserInfo: true
    })
  },
  goToIndex:function(){
    var that = this
    //console.log(that)
    if(that.data.tiaozhuan){
    wx.navigateTo({
      url: that.data.gourl
    })
    }else{
      wx.switchTab({
        url: that.data.gourl
      });
    }
  },
  onLoad:function(){
    wx.clearStorageSync()
  },
  onShow:function(){


    var that = this;
    that.setData({
      tiaozhuan: true,
      gourl: '/pages/setpersoninfo/setpersoninfo',
      tiaozhuan: true
    })
    wx.login({
      success: function (res) {
        if (res.code) {
          //发起网络请求
          wx.request({
            url: 'https://wechat.haizeix.com/login',
            header: {
              'Content-Type': 'application/json'
            },
            data: {
              code: res.code
            },
            success: function (res) {

              console.log(res);
             // console.log(that)
             // console.log('openid', res.data.openid)
              var personalinfovalue = {
                name: '',
                phone: '',
                learnid: '',
                qq:''
              }
              var personalopenid = {
                openid: ""
              }
              personalinfovalue.name = res.data.name;
              personalinfovalue.phone = res.data.phone;
              personalinfovalue.learnid = res.data.learnid;
              personalinfovalue.qq = res.data.qq;
              personalopenid.openid = res.data.openid;
              // console.log("进来了2")
              // console.log(res)
              //personalinfovalue.openid = res.data.openid;
              // console.log(personalinfovalue)
              wx.setStorage({
                key: 'haspersonalinfo',
                data: personalinfovalue,
                success() {
                }
              })
              wx.setStorage({
                key: 'personalopenid',
                data: personalopenid,
                success() {

                }
              })
             if(res.data.firstLogin){
               that.setData({
                 buttonClicked:false,
                 gourl:'/pages/setpersoninfo/setpersoninfo',
                 tiaozhuan:true
               })
             }else{
               that.setData({
                 buttonClicked: false,
                 gourl: '/pages/homepage/homepage',
                 tiaozhuan: false
               })
             }
            },
            fail: function (error) {
              //调用服务端登录接口失败
              // that.showInfo('调用接口失败');
              console.log(error);
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      },
      fail:function(res){
        console.log(res)
        console.log("登入错误")
      }
    })
    wx.getUserInfo({
      success: res => {
        app.globalData.userInfo = res.userInfo
        this.setData({
          userInfo: res.userInfo,
        })
      }
    })
  },
  // onShow:function(){
  //   wx.getUserInfo({
  //     success: res => {
  //       app.globalData.userInfo = res.userInfo
  //       this.setData({
  //         userInfo: res.userInfo,
  //       })
  //     }
  //   })

  //   // let that = this
  //   // let userInfo = wx.getStorageSync('userInfo')
  //   // if (!userInfo) {
  //   //   wx.navigateTo({
  //   //     url: "/pages/authorize/index"
  //   //   })
  //   // } else {
  //   //   that.setData({
  //   //     userInfo: userInfo
  //   //   })
  //   // }
  // },
  onReady: function(){
    var that = this;
    setTimeout(function(){
      that.setData({
        remind: ''
      });
    }, 1000);
    wx.onAccelerometerChange(function(res) {
      var angle = -(res.x*30).toFixed(1);
      if(angle>14){ angle=14; }
      else if(angle<-14){ angle=-14; }
      if(that.data.angle !== angle){
        that.setData({
          angle: angle
        });
      }
    });
  }
});