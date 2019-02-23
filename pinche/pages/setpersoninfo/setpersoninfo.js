// page/component/new-pages/user/address/address.js
Page({
  data:{
    imageLove: "/images/love.jpg",
    imageHaizei: "/images/haizei.png",
    haspersonalinfo:{
      name:'',
      phone:'',
      learnid:'',
      qq:''
    }
  },
  onLoad(){
    var self = this;
    wx.getStorage({
      key: 'haspersonalinfo',
      success: function(res){
        self.setData({
          haspersonalinfo : res.data
        })
      }
    })
  },
  formSubmit(e){
    const value = e.detail.value;
    // console.log("sssssssss111ss")
    // console.log(value.name.size)
    if (value.name && value.phone && value.learnid&&value.qq){
      wx.setStorage({
        key: 'haspersonalinfo',
        data: value,
        success(){
          //发起网络请求
          var userid=wx.getStorageSync('personalopenid')
          //console.log("sssssssssss")
          //console.log(userid)
          wx.request({
            url: 'https://wechat.haizeix.com/updataUserInfo',
            header:{
              "Content-Type": "application/x-www-form-urlencoded"
            },
            method:'POST',
            data: {
              'userid':userid.openid,
              'name':value.name,
              'phone':value.phone,
              'learnid':value.learnid,
              'qq': value.qq
            },
            success: function (res) {
                console.log(res)
              // console.log("成功")
              wx.navigateBack({
              })
            },
            fail: function (error) {
              console.log(error);
            }
          })

           wx.navigateBack();
        }
      })
    }else{
      wx.showModal({
        title:'提示',
        content:'请填写完整资料，也不要填写错误信息',
        showCancel:false
      })
    }
  }
})