package com.jisucloud.clawler.regagent.service;

import java.util.Iterator;
import java.util.Set;

import com.jisucloud.clawler.regagent.service.PapaSpider;
import com.jisucloud.clawler.regagent.service.impl._3c.*;
import com.jisucloud.clawler.regagent.service.impl.borrow.*;
import com.jisucloud.clawler.regagent.service.impl.car.*;
import com.jisucloud.clawler.regagent.service.impl.education.*;
import com.jisucloud.clawler.regagent.service.impl.email.*;
import com.jisucloud.clawler.regagent.service.impl.game.*;
import com.jisucloud.clawler.regagent.service.impl.health.*;
import com.jisucloud.clawler.regagent.service.impl.life.*;
import com.jisucloud.clawler.regagent.service.impl.money.*;
import com.jisucloud.clawler.regagent.service.impl.music.*;
import com.jisucloud.clawler.regagent.service.impl.news.*;
import com.jisucloud.clawler.regagent.service.impl.shop.*;
import com.jisucloud.clawler.regagent.service.impl.social.*;
import com.jisucloud.clawler.regagent.service.impl.trip.*;
import com.jisucloud.clawler.regagent.service.impl.video.*;
import com.jisucloud.clawler.regagent.service.impl.work.*;
import com.jisucloud.deepsearch.selenium.mitm.MitmServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PapaSpiderTester {

	public static interface PapaSpiderTestListener {
		
		public void testSuccess(Class<? extends PapaSpider> clz);
		
		public void testFailure(Class<? extends PapaSpider> clz);
		
	}
	
	public static void testing(Set<Class<? extends PapaSpider>> papaSpiders, PapaSpiderTestListener papaSpiderTestListener) {
		for (Iterator<Class<? extends PapaSpider>> iterator = papaSpiders.iterator(); iterator.hasNext();) {
			Class<? extends PapaSpider> clz = iterator.next();
			boolean success = false;
			try {
				PapaSpider instance =  clz.newInstance();
				Set<String> testTels = instance.getTestTelephones();
				if (testTels == null || testTels.size() < 2) {
					log.warn("无法测试，"+clz.getName()+" 最低需要两个不同的比较号码。一个确认已经注册，一个确认没有注册。");
					continue;
				}
				//如果全为true或者全为false，证明测试失败
				int trueCount = 0;
				int falseCount = 0;
				for (Iterator<String> iterator2 = testTels.iterator(); iterator2.hasNext();) {
					String tel = iterator2.next();
					if (instance.checkTelephone(tel)) {
						trueCount ++;
					}else {
						falseCount ++;
					}
					if (iterator2.hasNext()) {
						instance =  clz.newInstance();
					}
				}
				success = (trueCount != 0 && falseCount != 0);
			} catch (Exception e) {
				log.warn("测试"+clz.getName()+"异常", e);
			}finally {
				if (success) {
					papaSpiderTestListener.testSuccess(clz);
				}else {
					papaSpiderTestListener.testFailure(clz);
				}
			}
		}
	}
	
	/**
	 * 手工测试专用
	 * @param clz
	 */
	public static void testingWithPrint(Class<? extends PapaSpider> clz) {
		boolean success = false;
		try {
			PapaSpider instance =  clz.newInstance();
			Set<String> testTels = instance.getTestTelephones();
			if (testTels == null || testTels.size() < 2) {
				log.warn("无法测试，"+clz.getName()+" 最低需要两个不同的比较号码。一个确认已经注册，一个确认没有注册。");
				return;
			}
			//如果全为true或者全为false，证明测试失败
			int trueCount = 0;
			int falseCount = 0;
			for (Iterator<String> iterator2 = testTels.iterator(); iterator2.hasNext();) {
				String tel = iterator2.next();
				if (instance.checkTelephone(tel)) {
					log.info(tel+"已注册"+instance.platformName());
					trueCount ++;
				}else {
					log.info(tel+"未注册"+instance.platformName());
					falseCount ++;
				}
				if (iterator2.hasNext()) {
					instance =  clz.newInstance();
				}
			}
			success = (trueCount != 0 && falseCount != 0);
		} catch (Exception e) {
			log.warn("测试"+clz.getName()+"异常", e);
		}finally {
			if (success) {
				log.info("测试成功:"+clz.getName());
			}else {
				log.info("测试失败:"+clz.getName());
			}
			MitmServer.getInstance().stop();
		}
	}
	
	public static void main(String[] args) {
//		testingWithPrint(NiWoDaiSpider.class);
//		testingWithPrint(XiaoYingKaDaiService.class);
//		testingWithPrint(HengYiDaiService.class);
//		testingWithPrint(LianZiDaiSpider.class);
//		testingWithPrint(MinDaiService.class);
//		testingWithPrint(PharmacySpider.class);
//		testingWithPrint(WangdaiZhijiaSpider.class);
//		testingWithPrint(XYSpider.class);
//		testingWithPrint(ShangDeSpider.class);
//		testingWithPrint(TianNaSpider.class);
//		testingWithPrint(YiXinHuiMinSpider.class);
//		testingWithPrint(JuRenSpider.class);
//		testingWithPrint(BaiheWangSpider.class);
//		testingWithPrint(JiMuSpider.class);
//		testingWithPrint(DangDangWangSpider.class);
//		testingWithPrint(BoJinDaiSpider.class);
//		testingWithPrint(LanCaiWangSpider.class);
//		testingWithPrint(YiDaiWangSpider.class);
//		testingWithPrint(QiHu360Spider.class);
//		testingWithPrint(_2345DaiKuanWangSpider.class);
//		testingWithPrint(YouYuanWangSpider.class);
//		testingWithPrint(PingAnXiaoDaiSpdier.class);
//		testingWithPrint(ChinahrSpider.class);
//		testingWithPrint(HuaShengMiFuSpider.class);
//		testingWithPrint(QingYiDaiSpider.class);
//		testingWithPrint(PiPaSpider.class);
//		testingWithPrint(QuGuanZhangSpider.class);
//		testingWithPrint(HuXiuSpider.class);
//		testingWithPrint(NanXingSiRenYiShengSpider.class);
//		testingWithPrint(TianYaSpider.class);
//		testingWithPrint(YYSpider.class);
//		testingWithPrint(ZhongAnBaoXianSpider.class);
//		testingWithPrint(DaZhiHuiSpider.class);
//		testingWithPrint(AmazonSpider.class);
//		testingWithPrint(XuebaoSpider.class);
//		testingWithPrint(BaiXingWangSpider.class);
//		testingWithPrint(HengYiDaiSpider.class);
//		testingWithPrint(HuangJinQianBaoSpider.class);
//		testingWithPrint(TaxItsSpider.class);
//		testingWithPrint(RongYiJieSpider.class);
//		testingWithPrint(XiaoYingKaDaiSpider.class);
//		testingWithPrint(XinYongGuanJiaSpider.class);
//		testingWithPrint(QianQianJingTingSpider.class);
//		testingWithPrint(AiQiyiSpider.class);
//		testingWithPrint(AplipaySpider.class);
//		testingWithPrint(BaiduSpider.class);
//		testingWithPrint(ZhongGuoHunBoHuiSpider.class);
//		testingWithPrint(DuoDuoJinRongSpider.class);
//		testingWithPrint(JiaoGuanSpider.class);
//		testingWithPrint(LiXiangBaoSpider.class);
//		testingWithPrint(YinGuZaiXianSpider.class);
//		testingWithPrint(AiQianJinSpider.class);
//		testingWithPrint(MainMainSpider.class);
//		testingWithPrint(ZhiHuSpider.class);
//		testingWithPrint(TouTiaoSpider.class);
//		testingWithPrint(ZhongGuoZhiWangSpider.class);
//		testingWithPrint(CCTVSpider.class);
//		testingWithPrint(KuaiFangSpider.class);
//		testingWithPrint(ZealerTestSpider.class);
//		testingWithPrint(_1HaoDianSpider.class);
//		testingWithPrint(TaiPingYangMobileSpider.class);
//		testingWithPrint(KuaiKeJiSpider.class);
//		testingWithPrint(BossSpider.class);
//		testingWithPrint(XiaoZhuSpider.class);
//		testingWithPrint(RenHeWangSpider.class);
//		testingWithPrint(PaiDaiWangSpider.class);
//		testingWithPrint(MaFengWoSpider.class);
//		testingWithPrint(BookingSpider.class);
//		testingWithPrint(HongXiaoBaoSpider.class);
//		testingWithPrint(JinYuanBaoSpider.class);
//		testingWithPrint(QianTuWangSpider.class);
//		testingWithPrint(YiQiXiuSpider.class);
//		testingWithPrint(ZhaoShangDaiSpider.class);
//		testingWithPrint(MeiTuWangSpider.class);
//		testingWithPrint(MeiPaiSpider.class);
//		testingWithPrint(JuanPiSpider.class);
//		testingWithPrint(MeiMeiTouZiSpider.class);
//		testingWithPrint(GuangJinJinFuSpider.class);
//		testingWithPrint(_168JinFuSpider.class);
//		testingWithPrint(XiaoJinLiCaiSpider.class);
//		testingWithPrint(ChengTieZaiRongSpider.class);
//		testingWithPrint(QiLeRongSpider.class);
//		testingWithPrint(TaiRanJinRongSpider.class);
//		testingWithPrint(JuBaoPenSpider.class);
//		testingWithPrint(HeJiaJinRongSpider.class);
//		testingWithPrint(AiTouJinRongSpider.class);
//		testingWithPrint(HouBenJinRongSpider.class);
//		testingWithPrint(QiDianSpider.class);
//		testingWithPrint(ZhuBaoDaiSpider.class);
//		testingWithPrint(LianJinSuoSpider.class);
//		testingWithPrint(YiGangJinRongSpider.class);
//		testingWithPrint(QianBaSpider.class);
//		testingWithPrint(ZhongRongBaoSpider.class);
//		testingWithPrint(HouHeCaiFuSpider.class);
//		testingWithPrint(JinRiJieCaiSpider.class);
//		testingWithPrint(JinTiWangSpider.class);
//		testingWithPrint(GuangZhouEDaipider.class);
//		testingWithPrint(DeZhongJinRongSpider.class);
//		testingWithPrint(XiTouWangSpider.class);
//		testingWithPrint(YueShangDaiSpider.class);
//		testingWithPrint(HaiRongYiSpider.class);
//		testingWithPrint(DaRenDaiSpider.class);
//		testingWithPrint(EWeiDaiSpider.class);
//		testingWithPrint(DaiDaiXingLongSpider.class);
//		testingWithPrint(JuJinZiBenSpider.class);
//		testingWithPrint(FaceBookSpider.class);
//		testingWithPrint(TwitterSpider.class);
//		testingWithPrint(GoogleSpider.class);
//		testingWithPrint(BaoXiangJinRongSpider.class);
//		testingWithPrint(LanCaiWangSpider.class);
//		testingWithPrint(AoYouSpider.class);
//		testingWithPrint(TeambitionSpider.class);
//		testingWithPrint(YugusoftSpider.class);
//		testingWithPrint(MaoPuSheQuSpider.class);
//		testingWithPrint(KangTaiRenShouSpider.class);
//		testingWithPrint(KaiDiWangSpider.class);
//		testingWithPrint(JiFengLunTanSpider.class);
//		testingWithPrint(MuMaYiSpider.class);
//		testingWithPrint(AllianzSpider.class);
//		testingWithPrint(JiaYouBaoSpider.class);
//		testingWithPrint(ShouJiZhongGuoSpider.class);
//		testingWithPrint(ZhanKuSpider.class);
//		testingWithPrint(ShiJueZhongGuoSpider.class);
//		testingWithPrint(_56VideoSpider.class);
//		testingWithPrint(MeiZanChengSpider.class);
//		testingWithPrint(PharmacySpider.class);
//		testingWithPrint(FengHuangJinRongSpider.class);
//		testingWithPrint(DingDanHuiSpider.class);
		testingWithPrint(QQSpider.class);
	}
}
