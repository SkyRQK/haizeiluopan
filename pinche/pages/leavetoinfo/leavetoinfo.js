// pages/order/order.js
Page({
  data: {
    date:"2019-01-01",
    buttonClicked:false
  },
  formSubmit:function(res){

    
    var that=this

    var personalopenid = wx.getStorageSync('personalopenid');
    var personalcarinfo={
      startTime:"",
      carNum: "",
      remarks: ""
    }
    if (res.detail.value.carnum && res.detail.value.remarks){
      that.setData({
        buttonClicked: true
      })
      personalcarinfo.startTime = that.data.date
      personalcarinfo.carNum = res.detail.value.carnum
      personalcarinfo.remarks = res.detail.value.remarks
      wx.showToast({
        title: '成功',
        icon: 'success',
        duration: 1000//持续的时间
      })
      wx.request({
        url: 'https://wechat.haizeix.com/updatecarriagelist',
        method: 'POST',
        header: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: {
          userId:personalopenid.openid,
          startTime:that.data.date,
          carNum: res.detail.value.carnum,
          remarks: res.detail.value.remarks
        },
        success: function (res) {
          // wx.setStorage({
          //   key: 'personalcarinfo',
          //   data: personalcarinfo,
          //   success() {
          //   }
          // })
        //  console.log(res)
          if(res.data.success){
            wx.navigateBack({
            })

          }
          else{

          }
        },
        fail: function (res) {
        }
      })
    }
    else{

      wx.showToast({
        title: '车次和备注不能为空',
        icon: 'none',
        duration: 2000//持续的时间
      })
     // console.log("为空")
    }
  },
  datechange: function (res) {
   // console.log(res);
    this.setData({
      date: res.detail.value
    })
  },
  onLoad: function () {
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  }
})