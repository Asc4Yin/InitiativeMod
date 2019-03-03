package com.initstudios.initiative.network.packet;

import com.initstudios.initiative.network.PacketHandler;
import com.initstudios.initiative.pingTool.PingWrapper;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientSendPing implements IMessage, IMessageHandler<ClientSendPing, IMessage>
{
    private PingWrapper ping;

    public ClientSendPing() {}

    public ClientSendPing(PingWrapper ping)
    {
        this.ping = ping;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ping.writeToBuffer(buf);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        ping = PingWrapper.readFromBuffer(buf);
    }

    @Override
    public IMessage onMessage(ClientSendPing message, MessageContext ctx)
    {
        PacketHandler.INSTANCE.sendToDimension(new ServerBroadcastPing(message.ping), ctx.getServerHandler().player.world.provider.getDimension());

        return null;
    }
}
