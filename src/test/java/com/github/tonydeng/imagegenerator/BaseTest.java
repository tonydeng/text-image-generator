package com.github.tonydeng.imagegenerator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tonydeng on 2017/2/9.
 */
public class BaseTest {
    protected  Logger log = LoggerFactory.getLogger(this.getClass());
    protected final static String text = " \n"
            + "监测动态心电图23小时31分钟。 平均心率90 bpm， 最慢心率47 bpm， 发生于07-09 14:21， 最快心率185 bpm， 发生于07-09 17:12。 共分析心搏总数114242次， 有59次大于2.0秒的停搏， 其中最长为2.8秒， 发生于0时53分。\n" +
            "全程心房颤动。ST-T未见明显异常。哈哈哈收到发\n" +
            "动态心电图建议: 心房颤动(全程)，心室率偏快，伴长RR间歇 \n" +
            "(注:建议永久起搏器置入治疗)\n";

    protected final static List<String> texts = Arrays.asList(
            text,
            "一直以来都想尝试一下图片的中文识别，直到最近才有点空闲时间，主要目的是证实一下到底可不可行，正确率能否达到 95% 以上。自己从头写起十分费时间，因为图片处理很麻烦，所以我选用了Tesseract OCR。\n" +
                    "\n" +
                    "所谓 OCR(Optical Character Recognition)是指对文本资料进行扫描，然后对图像文件进行分析处理，获取文字和版面信息的过程。OCR是图像识别领域中的一个子领域，该领域专注于对图片中的文字信息进行识别并转换成能被常规文本编辑器编辑的文本。\n" +
                    "\n" +
                    "Tesseract介绍\n" +
                    "Tesseract(/‘tesərækt/) 这个词的意思是”超立方体”，指的是几何学里的四维标准方体，又称”正八胞体”，是一款被广泛使用的开源 OCR 工具。\n" +
                    "\n" +
                    "tesseract\n" +
                    "\n" +
                    "Tesseract 已经有 30 年历史，开始它是惠普实验室于1985年开始研发的一款专利软件，到1995年一件成为OCR业界内最准确的识别引擎之一。然而，HP不久便决定放弃OCR业务，Tesseract从此尘封。数年之后，HP意识到与其将Tesseract束之高阁，还不如贡献给开源，让其重焕新生。在 2005 年，Tesseract由美国内华达州信息技术研究所获得，并求助于Google对Tesseract进行改进、消除Bug、优化工作，并开源，其后一直由 Google 赞助进行后续的开发和维护。因为其免费与较好的效果，许多的个人开发者以及一些较小的团队在使用着 Tesseract ，诸如验证码识别、车牌号识别等应用中，不难见到 Tesseract 的身影。\n" +
                    "\n" +
                    "现在Tesseract托管在Github上，大家有兴趣可以上Github上Star或Frok该项目。\n" +
                    "\n" +
                    "安装\n" +
                    "Mac OSX\n" +
                    "\n" +
                    "在Mac上安装Tesseract是一件非常简单的事情，我们还是使用brew来进行安装。\n" +
                    "\n" +
                    "brew install tesseract\n" +
                    "➜ tesseract --version\n" +
                    "tesseract 3.04.01\n" +
                    " leptonica-1.73\n" +
                    "  libjpeg 8d : libpng 1.6.23 : libtiff 4.0.6 : zlib 1.2.5\n" +
                    "不过，如果你只是用上述命令来安装Tesseract的话，就会发现，只支持英文，因为它只默认安装了eng的语言包。如果我们需要识别其他的语言该如何来办呢？\n" +
                    "\n" +
                    "安装指定的语言包：\n" +
                    "\n" +
                    "brew intsall tesseract\n" +
                    "cd /usr/local/Cellar/tesseract/{version}/share/tessdata\n" +
                    "wget https://github.com/tesseract-ocr/tessdata/raw/master/chi_sim.traineddata\n" +
                    "使用brew安装所有语言包：\n" +
                    "\n" +
                    "brew install tesseract --all-languages\n" +
                    "其他平台安装\n" +
                    "\n" +
                    "更多Tesseract的安装可以查看这儿Install Tesseract via pre-built binary package或 build it from source",

            "命令行使用\n" +
                    "这里只见到讲一下Tesseract识别图像的基本用法，关于训练和开发将来在另开新篇来专门讲述。\n" +
                    "\n" +
                    "由于Tesseract只提供命令行工具，这里讲的用法对于Linux和Windows平台都适用。\n" +
                    "\n" +
                    "首先可以通过\"--list-langs\"来查看哪些可用的“语言”，如果之前的TESSDATA_PREFIX环境变量没有设置错误，将看到这样的输出：\n" +
                    "\n" +
                    "➜  ~tesseract --list-langs\n" +
                    "List of available languages (107):\n" +
                    "afr\n" +
                    "amh\n" +
                    "ara\n" +
                    "asm\n" +
                    "aze\n" +
                    "aze_cyrl\n" +
                    "bel\n" +
                    "ben\n" +
                    "bod\n" +
                    "bos\n" +
                    "bul\n" +
                    "cat\n" +
                    "ceb\n" +
                    "ces\n" +
                    "chi_sim\n" +
                    "chi_tra\n" +
                    "chr\n" +
                    "cym\n" +
                    "dan\n" +
                    ".....\n" +
                    "大家可以看到，我安装了107个语言包，其中,eng和chi_sim是Tesseract提供的英文和简体中文的语言文件。\n" +
                    "\n" +
                    "另外，要说明的是，这里的 “语言文件” 的本质是包含了某种 “自然语言” 的文字的特征等辅助识别的一些资源，但像 chi_sim 这个中文简体里也包含了英文字母与阿拉伯数字的资源。而我们也可以为了特定的用途而去训练产生对应的资源，并且可以给这个资源自定义一个名字。\n" +
                    "\n" +
                    "如果发现以上命令的输出为空，那应该去检查一下 TESSDATA_PREFIX 这个环境变量。在这个环境变量无误且 “语言文件” 存在的情况下，假设我们有一张名为 paper.png 的图片，则通过以下命令对图片进行识别，\n" +
                    "\n" +
                    "tesseract paper.png paper -l chi_sim\n" +
                    "第一个参数是待识别的图像的文件名\n" +
                    "第二个参数用于指定输出，如果希望直接输出而不是保存到文件，那么就使用 stdout，否则这个参数将会作为保存结果的文件的前缀\n" +
                    "-l chi_sim 这个应该很好理解，就是用来指定使用哪个 “语言文件”，如果是使用 英文(eng) ，这个参数可以不加，因为默认就是使用英文的 “语言文件” 来进行识别\n" +
                    "以上命令如不出错，结果将会保存到 paper.txt 这个文本文件中。\n" +
                    "\n" +
                    "此外 Tesseract 还提供非常丰富的可选参数来对识别过程进行调整，可用的参数及其默认值可以通过以下命令进行查看:\n" +
                    "\n" +
                    "tesseract --print-parameters\n" +
                    "参数的使用有两种:\n" +
                    "\n" +
                    "使用 -c 选项来设定单项参数的值，比如:\n" +
                    "tesseract paper.png paper -l chi_sim -c language_model_ngram_on=1\n" +
                    "允许使用多个 -c 选项来设置多个参数的值。\n" +
                    "\n" +
                    "将多项参数设置写入文件，然后在识别时使用该文件，比如:\n" +
                    "tesseract paper.png paper -l chi_sim tess.conf\n" +
                    "需要注意的是，如果使用配置文件，用作参数的配置文件名要放在最后面——这里也支持多个配置文件，但它们必须要在最后面。假如我有两个配置文件 tess_1.conf 和 tess_2.conf，那么这样是正确的:\n" +
                    "\n" +
                    "tesseract paper.png paper -l chi_sim tess_1.conf tess_2.conf\n" +
                    "而这样则是错误的:\n" +
                    "\n" +
                    "tesseract paper.png paper tess_1.conf -l chi_sim tess_2.conf\n" +
                    "至于 Tesseract 那些参数各有什么含义，官方没有提供任何文档来进行解释，这里有一个链接提供了部分参数的用处说明，应该是阅读了 Tesseract 源代码后得到的结论。\n" +
                    "\n" +
                    "另外\n" +
                    "关于Tesseract的文档，可以查看Tetesseract官方Blog\n" +
                    "\n",
            "从1956年正式提出人工智能学科算起，40多年来，取得长足的发展，成为一门广泛的交叉和前沿科学。总的说来，人工智能的目的就是让计算机这台机器能够象人一样思考。如果希望做出一台能够思考的机器，那就必须知道什么是思考，更进一步讲就是什么是智慧。什么样的机器才是智慧的呢？科学家已经作出了汽车，火车，飞机，收音机等等，它们模仿我们身体器官的功能，但是能不能模仿人类大脑的功能呢？到目前为止，我们也仅仅知道这个装在我们天灵盖里面的东西是由数十亿个神经细胞组成的器官，我们对这个东西知之甚少，模仿它或许是天下最困难的事情了。\n" +
                    "　　当计算机出现后，人类开始真正有了一个可以模拟人类思维的工具，在以后的岁月中，无数科学家为这个目标努力着。现在人工智能已经不再是几个科学家的专利了，全世界几乎所有大学的计算机系都有人在研究这门学科，学习计算机的大学生也必须学习这样一门课程，在大家不懈的努力下，现在计算机似乎已经变得十分聪明了。例如，1997年5月，IBM公司研制的深蓝（Deep Blue）计算机战胜了国际象棋大师卡斯帕洛夫（Kasparov）。大家或许不会注意到，在一些地方计算机帮助人进行其它原来只属于人类的工"
    );

    private Font previouslyUsedFont = new Font("SansSerif", Font.PLAIN, 12);

    @Test
    public void test() throws IOException {
        log.info("this test......");
        Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for(Font font:fonts){
//            log.info("{} equals {}",font,previouslyUsedFont.equals(font));
            if(previouslyUsedFont.getFontName().equals(font.getFontName())){
                log.info("{}",font);
            }
        }

    }
}
