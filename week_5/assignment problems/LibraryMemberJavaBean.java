public class LibraryMemberJavaBean {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMemberJavaBean() {}

    public String getMembershipId() { return membershipId; }

    public void setMembershipId(String id) {
        if (membershipId == null) membershipId = id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public boolean isPremiumMember() { return premiumMember; }
    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        securityAnswerHash = Integer.toHexString(answer.hashCode());
    }

    public static void main(String[] args) {
        LibraryMemberJavaBean m = new LibraryMemberJavaBean();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);
        m.setSecurityAnswer("BlueMountain");

        m.setMembershipId("FAKE-0000");

        System.out.println("Membership ID: " + m.getMembershipId());
        System.out.println("Name: " + m.getName());
        System.out.println("Premium: " + m.isPremiumMember());
        // securityAnswer has no getter.
    }
}
