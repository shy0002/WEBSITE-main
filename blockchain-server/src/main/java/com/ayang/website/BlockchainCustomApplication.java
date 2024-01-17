package com.ayang.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 盛浩洋
 * @date 2023/12/4 13:41
 */
@SpringBootApplication(scanBasePackages = {"com.ayang.website"})
@EnableScheduling
@EnableConfigurationProperties
public class BlockchainCustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlockchainCustomApplication.class, args);
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