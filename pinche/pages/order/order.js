// pages/order/order.js
Page({
  data: {
    beginarray: ['哈工程十六公寓', '哈工程大美', '哈工程小美', '启航活动中心','哈工程东门','哈工程北门','哈尔滨东站','哈尔滨站','哈尔滨西站','哈尔滨机场'],
    arrivearray: ['哈工程十六公寓', '哈工程大美', '哈工程小美', '启航活动中心', '哈工程东门', '哈工程北门', '哈尔滨东站', '哈尔滨站', '哈尔滨西站', '哈尔滨机场'],
    Seats:[1,2,3,4],
    seatsindex:0,
    disabled: true,
    beginindex: 0,
    arriveindex:0,
    value: "12:00",
    date:"2019-01-01",
    buttonClicked: false
  },
  formSubmit:function(res){
    var that = this
    that.setData({
      buttonClicked:true
    })
      // console.log(res)
      // console.log(that)
    var personalopenid = wx.getStorageSync('personalopenid');
    if (res.detail.value.captainchange && res.detail.value.phonechange && res.detail.value.carintroducechange && res.detail.value.palyerrequirchange){
      wx.showToast({
        title: '创建成功',
        icon: 'success',
        duration: 2000//持续的时间
      })
      wx.request({
        url: 'https://wechat.haizeix.com/insertcarslist',
        method: 'POST',
        header: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        data: {
          captainid: personalopenid.openid,
          BillDate: that.data.date,
          Beginposition: that.data.beginarray[that.data.beginindex],
          Arriveposition: that.data.beginarray[that.data.arriveindex],
          Palyerrequir: res.detail.value.palyerrequirchange,
          Carintroduce: res.detail.value.carintroducechange,
          ClearTime: that.data.value,
          Captain: res.detail.value.captainchange,
          Seats: that.data.Seats[that.data.seatsindex],
          phone: res.detail.value.phonechange
        },
        success: function (res) {
          wx.navigateBack({
          })
        },
        fail: function (res) {
        }
      })
    }
    else{
      wx.showToast({
        title: '输入数据为空',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },


  // captainchange:function(res){
  //   console.log(res.detial.value);

  // },
  // phonechange:function(res){
  //   console.log(res)
  // },
  // palyerrequirchange:function(res){
  //   console.log(res)
  // },
  // carintroducechange:function(res){
  //   console.log(res)
  // },
  // nextStep: function () {
  //   wx.request({
  //     url: 'http://localhost:8999/joincars',
  //     method: 'POST',
  //     header: {
  //       'Content-Type': 'application/json'
  //     },
  //     data: {
  //       groupId: index,
  //       userId: personalopenid.openid
  //     },
  //     success: function (res) {
  //     },
  //     fail: function (res) {
  //     }
  //   })
  // },
  beginpickerchange: function (res) {
    console.log(res);
    this.setData({
      beginindex: res.detail.value
    })
  },
  arrivepickerchange: function (res) {
    console.log(res);
    this.setData({
      arriveindex: res.detail.value
    })
  },
  seatschange: function (res) {
    console.log(res);
    this.setData({
      seatsindex: res.detail.value
    })
  },
  timechange: function (res) {
    console.log(res);
    this.setData({
      value: res.detail.value
    })
  },
  datechange: function (res) {
    console.log(res);
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