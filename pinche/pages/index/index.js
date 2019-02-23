// pages/shoporder/shoporder.js
Page({
  data: {
    Cars: [],
    arrivearray: ['哈尔滨东站', '哈尔滨站', '哈尔滨西站', '哈尔滨机场'],
    arriveindex: 0,
    date: "2019-01-01",
  },
  onItemClick: function (e) {
    var that=this;
    //console.log(e);
    var index = e.target.id;
    //console.log(index);
    var personalopenid = wx.getStorageSync('personalopenid');
    wx.showModal({
      title: '提示',
      content: '是否加入队伍',
      success: function (res) {
        if (res.confirm) {
          //console.log(index);
          //console.log(personalopenid.openid)
          wx.request({
            url: 'https://wechat.haizeix.com/joincars',
            method: 'POST',
            header: {
              "Content-Type": "application/x-www-form-urlencoded"
            },
            data:{
              groupId:index,
              userId: personalopenid.openid
            },
            success: function (res) {
              // console.log(res)
              if(res.data.success){
                wx.showToast({
                  title: '成功',
                  icon: 'success',
                  duration: 2000//持续的时间
                })
                that.onLoad();
              }
              else{
                if(res.data.errMsg=="have exist"){
                wx.showToast({
                  title: '你已经加入了',
                  icon: 'none',
                  duration: 2000//持续的时间
                })
                }else{
                  wx.showToast({
                    title: '人数已满',
                    icon: 'none',
                    duration: 2000//持续的时间
                  })
                }

              }

            },
            fail: function (res) {

            }
          })

        } else if (res.cancel) {
          

        }
      }
    })
  },
  goToinsertcar: function () {

    //跳转到成功页面
    wx.navigateTo({
      url: '/pages/order/order'
    })
    // wx.request({
    //   url: '',
    //   dataType: 'json',
    //   method: 'GET',
    //   header: {
    //     'content-type': 'application/json'
    //   },
    //   success: function (res) {
    //     // success
    //     console.log(res);
    //   }
    // })
  },
  // onShareAppMessage: function () {
  //   return {
  //     title: '订单列表',
  //     desc: '好多好多东西，没钱了',
  //     path: 'www.baidu.com'
  //   }
  // },
  onLoad: function () {
  },
  onShow:function(){
    var that = this;
    wx.request({
      url: 'https://wechat.haizeix.com/findallcarlist',
      method: 'GET',
      success: function (res) {
        that.setData({
          Cars: res.data
        })
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
  },
  datechange: function (res) {

    this.setData({
      date: res.detail.value
    })
  },
  arrivepickerchange: function (res) {

    this.setData({
      arriveindex: res.detail.value
    })
  },
  searchCar:function(){
    var that=this

    wx.request({
      url: 'https://wechat.haizeix.com/searchCar',
      method: 'GET',
      data:{
        billdate:that.data.date,
        arriveposition:that.data.arrivearray[that.data.arriveindex]
      },
      success: function (res) {
        that.setData({
          Cars: res.data
        })
      },
      fail: function (res) {
        console.log(res.data);
        console.log('is failed')
      }
    })
  
  }
})