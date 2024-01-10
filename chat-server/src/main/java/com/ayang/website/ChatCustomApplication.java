package com.ayang.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 盛浩洋
 * @date 2023/12/4 13:41
 */
@SpringBootApplication(scanBasePackages = {"com.ayang.website"})
@EnableScheduling
public class ChatCustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatCustomApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " #   #        #    #    #     #       ##       #         #              #  #   #                  #\n" +
                " ###############    #    ##  ###      ## # ######        ##             ## ##  ##      #############\n" +
                " ##  ##      ##     ##    # ##     #########  ##         ##            # #  # ##  #              ##\n" +
                " ##  ###   # ##     ##  ########     ## ##  # ##         ##     #     ##############     #       ##\n" +
                " ##  # ########   #        ##       ##  ##   ##      #############   ###         ##       ##     ##\n" +
                " ## #  ## ## ##    #  #    ## #       ###   ## #         ##    ##     #       #  #         ##    ##\n" +
                " ####  ## ## ##    ## # ########     ## ## ##  ###       ##    ##        #######           ##    ##\n" +
                " ## ## ## ## ##    # #     ##      ##   # #     #        ##    ##            ##            #    ###\n" +
                " ##  #### ## ##      #     ##           ##     #        ##     ##           ##                ## ##\n" +
                " ##  ####### ##     ##     ##  #    #############       ##     ##           ##   #          ###  ##\n" +
                " ##  ###  #  ##   # #  ##########       ##    ##        ##     ##     #############       ####   ##\n" +
                " #####       ##    ##      ##          ##     ##       ##      ##           ##          ####     ##\n" +
                " ## #        ##    ##      ##          ##     ##       ##      ##           ##        ####       ##\n" +
                " ##       #  ##    ###     ##         ##      ##      ##      ##            ##         #         ##\n" +
                " ##        ####    ###     ##        ##     ###      ##     ####          ####                 ####\n" +
                " ##         ##      #      ##      ##        #      #         #             #                    #");
    }
}