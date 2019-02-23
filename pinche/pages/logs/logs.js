
Page({
  data: {
    thumb: '',
    nickname: '',
    hasUserInfo: false,
    ifhaspersonalinfo:false,
    haspersonalinfo: {
      name: '',
      phone: '',
      learnid: '',
      qq:''
    },
    mainCarsList:[],
    personalopenid:''
  },
  getUserInfo(info) {
    const userInfo = info.detail.userInfo
    this.setData({
      userInfo,
      hasUserInfo: true
    })
  },
  onLoad: function () {
    var self = this;
    /**
     * 获取用户信息
     */
    wx.getUserInfo({
      success: function (res) {
        self.setData({
          thumb: res.userInfo.avatarUrl,
          nickname: res.userInfo.nickName
        })
      }
    })
  },
  onShow: function(){
    var self = this;
    /**
     * 获取本地缓存 地址信息
     */
    wx.getStorage({
      key: 'haspersonalinfo',
      success: function (res) {
      //  console.log(1111)
      //    console.log(res)
        self.setData({
          ifhaspersonalinfo: true,
          haspersonalinfo: res.data
        })
      }
    })

    wx.getStorage({
      key: 'personalopenid',
      success: function (res) {
        // console.log("ssssssssssssss")
        // console.log(res)
        // console.log(self)
        self.setData({
          personalopenid: res.data.openid
        })

        wx.request({
          url: 'https://wechat.haizeix.com/findcarlistbyuserid',
          header: {
            'Content-Type': 'application/json'
          },
          data: {
            "userId": self.data.personalopenid
          },
          success: function (res) {
           // console.log(res)
            self.setData({
              mainCarsList: res.data
            })
          },
          fail: function (error) {
            //调用服务端登录接口失败
            // that.showInfo('调用接口失败');
            console.log(error);
          }
        })
      }
    })
  },
  lookcardetail:function(res){
  //  console.log(res)
  //  console.log(res.target.id)
    wx.navigateTo({
      url: '/pages/cardetail/cardetail?groupid=' + res.target.id,
    })
  },
  aboutus:function(res){
    wx.showModal({
      title: '关于我们',
      content: '该小程序是由在校学生运行，如有任何问题，或吐槽，请联系QQ1137554811',
      success: function (res) {
      }
   })
  }
})