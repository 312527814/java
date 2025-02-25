/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codingapi.txlcn.txmsg.netty.handler;

import com.codingapi.txlcn.txmsg.RpcAnswer;
import com.codingapi.txlcn.txmsg.dto.RpcCmd;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/10
 *
 * @author ujued
 */
@ChannelHandler.Sharable
@Slf4j
@Component
public class RpcAnswerHandler extends SimpleChannelInboundHandler<RpcCmd> {

    @Autowired
    private RpcAnswer rpcClientAnswer;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcCmd cmd) {
        String remoteKey = ctx.channel().remoteAddress().toString();
        cmd.setRemoteKey(remoteKey);
        System.out.println("cmd.getMsg().getAction() => " + cmd.getMsg().getAction());
//        if (cmd.getMsg().getAction().equals("notifyGroup")) {
//
//            System.out.println("notifyGroup..............");
//            try {
//                Thread.sleep(1000 * 5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        rpcClientAnswer.callback(cmd);
    }
}
